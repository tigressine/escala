package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import escala.GameState;

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
    String[] regionNames = {"Asia", "Eastern Europe", "Latin America", "Middle East", 
            "North Africa", "North America", "Ocenia", "South Africa", "South America", "Western Europe"};
    BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;
    
    
    public Map(){
        
        try { 
            background = ImageIO.read(new File("assets/Background.png"));
            
            //load all regions
            for(int i = 0; i < NUM_REGIONS; i++)
                regions[i] = ImageIO.read(new File("assets/" + regionNames[i] + ".png"));
            
            //load all glow regions
            // TODO:
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    public void renderMap(Graphics2D g) {
        GameState myGame = GameState.getInstance();
        
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
            g.drawImage(regions[i], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        
        // TODO use cursor location to determine which region should be highlighted
        
        // TODO render highlighted region
        
    }

}
