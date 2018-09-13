package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import escala.GameState;
import java.awt.*;
import javax.swing.JFrame;

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
    
    Polygon[] regionsPoly = new Polygon[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;
    
    
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

        initPolyRegions();
        
    }

    public void initPolyRegions(){
        // right now this is just brute force... need to load these automatically

        int asiaX[] = {820, 782, 796, 835, 920, 926, 999, 1037, 1037, 929, 910};
        int asiaY[] = {333, 266, 196, 150, 157, 138, 142, 184,  221,  363, 348

};
        regionsPoly[0] = new Polygon(asiaX, asiaY, asiaX.length);
        
        int eEuropeX[] = {985, 983, 696, 706, 642, 617, 585, 631, 830, 1077,  1052};
        int eEuropeY[] = {191, 168, 156, 194, 178, 219, 179, 80,  57,   90,   161

};
        regionsPoly[1] = new Polygon(eEuropeX, eEuropeY, eEuropeX.length);
        
        int lAmericaX[] = {112, 153, 183, 293, 238, 127};
        int lAmericaY[] = {227, 231, 256, 278, 336, 273};
        regionsPoly[2] = new Polygon(lAmericaX, lAmericaY, lAmericaX.length);
        
        int mEastX[] = {695, 625, 690, 689, 751, 830, 791};
        int mEastY[] = {317, 198, 184, 152, 135, 161, 269};
        regionsPoly[3] = new Polygon(mEastX, mEastY, mEastX.length);
        
        int nAfricaX[] = {504, 469, 469, 510, 578, 658, 702, 729, 706
};
        int nAfricaY[] = {356, 316, 271, 219, 209, 233, 317, 312, 356};
        regionsPoly[4] = new Polygon(nAfricaX, nAfricaY, nAfricaX.length);
        
        int nAmericaX[] = {39,  65, 349, 519, 509, 246, 177, 151, 112, 100, 122};
        int nAmericaY[] = {137, 93, 43,  44,  108, 261, 264, 234, 231, 217, 128};
        regionsPoly[5] = new Polygon(nAmericaX, nAmericaY, nAmericaX.length);
        
        int oceniaX[] = {923, 892, 985, 1117, 1139, 1086};
        int oceniaY[] = {510, 337, 285, 377,  525,  561

};
        regionsPoly[6] = new Polygon(oceniaX, oceniaY, oceniaX.length);
        
        int sAfricaX[] = {566, 705, 728, 711, 634, 603
};
        int sAfricaY[] = {352, 352, 415, 472, 508, 508

};
        regionsPoly[7] = new Polygon(sAfricaX, sAfricaY, sAfricaX.length);
        
        int sAmericaX[] = {251, 408, 336, 287, 226};
        int sAmericaY[] = {311, 386, 590, 557, 365};
        regionsPoly[8] = new Polygon(sAmericaX, sAmericaY, sAmericaX.length);
        
        int wEuropeX[] = {503, 506, 621, 636, 592};
        int wEuropeY[] = {214, 141, 77,  111, 222};
        regionsPoly[9] = new Polygon(wEuropeX, wEuropeY, wEuropeX.length);
        
    }

    public void renderMap(Graphics2D g) {
        GameState myGame = GameState.getInstance();


        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = myGame.getFrame().getLocation();

        int skip = regionSelect(new Point((p.x - r.x),(p.y - r.y - 23)),myGame.getScale());

        System.out.println("(" + (p.x - r.x) + 
              ", " + 
              (p.y - r.y - 23) + ")");
        
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

    private int regionSelect(Point p, double scale)
    {
        /*//TODO Replace with Polygons for higher accuracy 
        Rectangle [] regionsRect = {
            new Rectangle(819, 166, 1036, 335),
            new Rectangle(624, 75, 1049, 182),
            new Rectangle(113, 230, 284, 307),
            new Rectangle(641, 165, 782, 308),
            new Rectangle(450, 219, 721, 357),
            new Rectangle(16, 38, 401, 257),
            new Rectangle(882, 336, 1443, 540),
            new Rectangle(567, 350, 717, 510),
            new Rectangle(213, 334, 394, 590),
            new Rectangle(505, 64, 599, 208)};

        //To be tested, Scale might not be working
        //Change point to doubles for polygons*/
        p = new Point((int)(p.x * scale), (int)(p.y * scale));

        for(int i = 0; i < NUM_REGIONS; i++)
            if(regionsPoly[i].contains(p))
                return i;

        return Integer.MAX_VALUE;
    }

}
