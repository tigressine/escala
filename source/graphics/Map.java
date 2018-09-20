// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala.graphics;

import escala.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

// Holds the map and rendered regions.
public class Map {
    private Font font;
    private int imageWidth;
    private int imageHeight;
    private GameState state;
    private BufferedImage background;

    // Create a new map.
    public Map(GameState state) {
        imageWidth = 1152;
        imageHeight = 648;
        this.state = state;
        font = new Font("serif", Font.BOLD, 48);

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
    public void renderMap(Graphics2D g) {
        Logic logic = Logic.getInstance();
        Point r = state.getFrame().getLocation();
        Point p = MouseInfo.getPointerInfo().getLocation();

        // Render the background.
        if (background != null) {
            g.drawImage(
                background, 0, 0,
                state.getWidth(),
                state.getHeight(),
                0, 0, imageWidth,
                imageHeight,
                null
            );
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
        Point containsPoint = new Point(p.x - r.x, p.y - r.y - 23);
        for (Region region : regions) {
            if (selectedRegion == null && region.contains(containsPoint, state.getScale())) {
                selectedRegion = region;
            }
            else {
                g.drawImage(
                    region.getImage(), 0, 0,
                    state.getWidth(),
                    state.getHeight(),
                    0, 0, imageWidth,
                    imageHeight,
                    null
                );  
            }
        }

        // Draw the selected/hovered region.
        if (selectedRegion != null) {
            g.drawImage(
                selectedRegion.getOutline(),
                0, 0, state.getWidth(),
                state.getHeight(),
                0, 0, imageWidth,
                imageHeight,
                null
            );
        }
        
        // Show stats on the screen.
        g.setFont(font);
        g.drawString(logic.cashToString(),10,630);
        g.drawString(logic.shareToString(),1000,630);
    }
}
