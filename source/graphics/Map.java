// Part of Escala.
// Written by Spencer Phillips.

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
import java.awt.Font;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.Insets;

import java.awt.BasicStroke;


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

public class Map {
    
    private static final int NUM_REGIONS = 10;
    BufferedImage background = null;
    BufferedImage[] regions = new BufferedImage[NUM_REGIONS];
    String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Oceania", "SouthAfrica", "SouthAmerica", "WesternEurope"};
    BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;


    static int skip = Integer.MAX_VALUE;
    static boolean clicked = false;

    private GameState state;

    public Map(GameState state){
        this.state = state;

        try {
            URL url = getClass().getResource("/data/assets/Background.png");
            String path = URLDecoder.decode(url.getPath(), "UTF-8");
            background = ImageIO.read(new File(path));
            
            //load all regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + ".png"); 
                path = URLDecoder.decode(url.getPath(), "UTF-8");
                regions[i] = ImageIO.read(new File(path));
            }
            
            //load all glow regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + "Glow.png"); 
                path = URLDecoder.decode(url.getPath(), "UTF-8");
                glowRegions[i] = ImageIO.read(new File(path));
            }
            
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public void renderMap(Graphics2D g) {
        Logic logic = Logic.getInstance();
        PolyMouseList poly = PolyMouseList.getInstance();
    
        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = state.getFrame().getLocation();
        Insets margin = state.getFrame().getInsets();
        double scale = state.getScale();

        if(clicked == false)
            skip = poly.contains(new Point((p.x - r.x - margin.left),(p.y - r.y - margin.top)), scale);

        //System.out.println((p.x - r.x - margin.left) + " " + (p.y - r.y - margin.top));
        
        // render background
        if(background != null)
            g.drawImage(background, 0, 0, state.getWidth(), state.getHeight(), 0, 0, imageWidth, imageHeight, null);
        else{
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, state.getWidth(), state.getHeight());
        }
        
        // render each region
        for(int i = 0; i < NUM_REGIONS; i++)
        {
           if(i == skip)
               continue;

            g.drawImage(regions[i], 0, 0, state.getWidth(), state.getHeight(), 0, 0, imageWidth, imageHeight, null);
        }

        if(skip < NUM_REGIONS)
            g.drawImage(glowRegions[skip], 0, 0, state.getWidth(), state.getHeight(), 0, 0, imageWidth, imageHeight, null);  
        
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

    public static void setSkip(int reg)
    {
        skip = reg;
    }

    public static void setClick(boolean click)
    {
        clicked = click;
    }
}
