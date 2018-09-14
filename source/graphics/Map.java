package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import escala.GameState;
import escala.Logic;
import java.awt.Font;

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

    Font font = new Font("serif", Font.BOLD, 48);
    
    
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
            // TODO:
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    public void renderMap(Graphics2D g) {
        GameState myGame = GameState.getInstance();
        Logic logic = Logic.getInstance();
        
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

        //Stats on Screen
    // TODO Change to Scalable once merged to Click-able version 
        g.setFont(font);
        g.drawString(logic.cashToString(),10,630);
        g.drawString(logic.shareToString(),1000,630);
       
    }

}
