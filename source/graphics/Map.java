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

public class Map {
    
    private static final int NUM_REGIONS = 10;
    BufferedImage background = null;
    BufferedImage[] regions = new BufferedImage[NUM_REGIONS];
    String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Oceania", "SouthAfrica", "SouthAmerica", "WesternEurope"};
    BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;

    Font font = new Font("serif", Font.BOLD, 48);
    
    PolyMouseList poly = PolyMouseList.getInstance();
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
                regions[i] = state.getRegion(regionNames[i]).getImage();
            }
            
            //load all glow regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                glowRegions[i] = state.getRegion(regionNames[i]).getOutline();
            }
            
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public void renderMap(Graphics2D g) {
        Logic logic = Logic.getInstance();

        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = state.getFrame().getLocation();

        if(clicked == false)
            skip = poly.contains(new Point((p.x - r.x),(p.y - r.y - 23)), state.getScale());

        //System.out.println((p.x - r.x) + " " + p.y - r.y - 23));
        
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
        g.setFont(font);
        g.drawString(logic.cashToString(),10,630);
        g.drawString(logic.shareToString(),1000,630);
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
