package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import escala.GameState;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.MouseInfo;


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
            "NorthAfrica", "NorthAmerica", "Ocenia", "SouthAfrica", "SouthAmerica", "WesternEurope"};
    BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;

    PolyMouseList poly = PolyMouseList.getInstance();
    static int skip = Integer.MAX_VALUE;
    static boolean clicked = false;

    public Map(){
        try {
            URL url = getClass().getResource("/data/assets/Background.png");
            background = ImageIO.read(new File(url.getPath()));
            
            //load all regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + ".png"); 
                regions[i] = ImageIO.read(new File(url.getPath()));
            }
            
            //load all glow regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + "Glow.png"); 
                glowRegions[i] = ImageIO.read(new File(url.getPath()));
            }
            
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public void renderMap(Graphics2D g) {
        GameState myGame = GameState.getInstance();


        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = myGame.getFrame().getLocation();

        if(clicked == false)
            skip = poly.contains(new Point((p.x - r.x),(p.y - r.y - 23)), myGame.getScale());

        //System.out.println((p.x - r.x) + " " + p.y - r.y - 23));
        
        // render background
        if(background != null)
            //g.drawImage(Image, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, observer)
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

