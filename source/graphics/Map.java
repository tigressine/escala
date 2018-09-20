// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.io.IOException;
import javax.imageio.ImageIO;
import escala.GameState;
import escala.Logic;
import escala.Region;
import java.awt.Font;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.Insets;
import java.awt.BasicStroke;
import java.util.ArrayList;

/*
 * 
 * NOTE::: if map is not rendered properly, double check path and names:
 * 
 * TODO::: Ideally, we will pull NUM_REGIONS and regionNames from database
 *              regionNames can be stored as paths...
 * 
 *      Use cursor location to determine which region to highlight
 * 
 * NOTE::: To improve game performance, reduce image size and pre-stretch all images when loading.
 * */

// Holds the map and rendered regions.
public class Map {

    private int imageWidth;
    private int imageHeight;

    private GameState state;
    private BufferedImage background;

    // Create a new map.
    public Map(GameState state) {
        imageWidth = 1152;
        imageHeight = 648;
        this.state = state;

        try {
            URL url = getClass().getResource("/data/assets/Background.png");
            String path = URLDecoder.decode(url.getPath(), "UTF-8");
            background = ImageIO.read(new File(path));    
        }
        catch (IOException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }
    }

    // Render the map onto the screen.
    public void renderMap(Graphics2D g) 
    {
        Logic logic = Logic.getInstance();
        Point r = state.getFrame().getLocation();
        Point p = MouseInfo.getPointerInfo().getLocation();
        Insets margin = state.getFrame().getInsets();
        double scale = state.getScale();

        p = new Point((p.x - r.x - margin.left),(p.y - r.y - margin.top));

        p.x = (int)((1/scale) * (double)p.x);
        p.y = (int)((1/scale) * (double)p.y);

        // Render the background.
        if (background != null) {
            g.drawImage(background, 0, 0,
                        state.getWidth(),
                        state.getHeight(),
                        0, 0, imageWidth,
                        imageHeight,
                        null);
        }
        else {
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, state.getWidth(), state.getHeight());
        }

        // Determine if a region is already selected.
        Region selectedRegion = null;
        ArrayList<Region> regions = state.getAllRegions();
        for (Region region : regions) {
            if (region.isSelected()) {
                selectedRegion = region;
                break;
            }
        }

        // Render all regions and determine if a region is being hovered, if needed.
        for (Region region : regions) {
            if (selectedRegion == null && region.contains(p)) {
                selectedRegion = region;
            }
            else {
                g.drawImage(region.getImage(), 0, 0,
                            state.getWidth(),
                            state.getHeight(),
                            0, 0, imageWidth,
                            imageHeight,
                            null);  
            }
        }

        // Draw the selected/hovered region.
        if (selectedRegion != null) {
            g.drawImage(selectedRegion.getOutline(),
                        0, 0, state.getWidth(),
                        state.getHeight(),
                        0, 0, imageWidth,
                        imageHeight,
                        null);
        }
        
        //Stats on Screen
        g.setFont(new Font("serif", Font.BOLD, (int)(48 * scale)));
        g.drawString(logic.cashToString(),(int)((10 * scale) + margin.left),(int)((605 * scale) + margin.top));
        g.drawString(logic.shareToString(),(int)((1000 * scale) + margin.left),(int)((605 * scale)+ margin.top));
        g.setStroke(new BasicStroke((int)(2 * scale)));

        g.setFont(new Font("serif", Font.BOLD, (int)(14 * scale)));
        
        g.drawString("Product",(int)((403 * scale) + margin.left),(int)((590 * scale) + margin.top));
        g.setColor(Color.YELLOW);
        g.fillRect((int)((376 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(logic.getProd() * scale),(int)(20 * scale));
        g.setColor(Color.BLACK);
        g.drawRoundRect((int)((376 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(100 * scale),(int)(20 * scale),(int)(5 * scale),(int)(5 * scale));

        g.drawString("Marketing",(int)((545 * scale) + margin.left),(int)((590 * scale) + margin.top));
        g.setColor(Color.YELLOW);
        g.fillRect((int)((526 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(logic.getMark() * scale),(int)(20 * scale));
        g.setColor(Color.BLACK);
        g.drawRoundRect((int)((526 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(100 * scale),(int)(20 * scale),(int)(5 * scale),(int)(5 * scale));

        g.drawString("Logistics",(int)((700 * scale) + margin.left),(int)((590 * scale) + margin.top));
        g.setColor(Color.YELLOW);
        g.fillRect((int)((676 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(logic.getLog() * scale),(int)(20 * scale));
        g.setColor(Color.BLACK);
        g.drawRoundRect((int)((676 * scale) + margin.left),(int)((595 * scale) + margin.top),(int)(100 * scale),(int)(20 * scale),(int)(5 * scale),(int)(5 * scale));
    }
}
