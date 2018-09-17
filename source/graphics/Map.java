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

    public Map(){
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
        GameState myGame = GameState.getInstance();
        Logic logic = Logic.getInstance();

        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = myGame.getFrame().getLocation();
        Insets margin = myGame.getFrame().getInsets();
        double scale = myGame.getScale();

        if(clicked == false)
            skip = poly.contains(new Point((p.x - r.x - margin.left),(p.y - r.y - margin.top)), scale);

        //System.out.println((p.x - r.x) + " " + p.y - r.y - 23));
        
        // render background
        if(background != null)
            g.drawImage(background, 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        else{
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, myGame.getWidth(), myGame.getHeight());
        }
        
        // render each region
        for(int i = 0; i < NUM_REGIONS; i++)
        {
           if(i == skip)
               continue;

            g.drawImage(regions[i], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        }

        if(skip < NUM_REGIONS)
            g.drawImage(glowRegions[skip], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);  
        
                //Stats on Screen
        g.setFont(font);
        g.drawString(logic.cashToString(),(int)((10 * scale) + margin.left),(int)((605 * scale) + margin.top));
        g.drawString(logic.shareToString(),(int)((1000 * scale) + margin.left),(int)((605 * scale)+ margin.top));
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
