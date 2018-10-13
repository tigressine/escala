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
import escala.structures.*;

// Holds the map and rendered regions.
public class Map {

    private int imageWidth;
    private int imageHeight;

    private int osAdj;

    private Game game;

    // Create a new map.
    public Map(Game game) 
    {
        imageWidth = game.getWidth();
        imageHeight =  game.getHeight();
        this.game = game;
        osAdjust();
    }

    // Render the map onto the screen.
    public void renderMap(Graphics2D g) 
    {

        // Load the background and render it.
        BufferedImage background = game.getBackground();
        if (background != null) {
            g.drawImage(background, 0, 0,
                        game.getWidth(),
                        game.getHeight(),
                        0, 0, imageWidth,
                        imageHeight,
                        null);
        }
        else {
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, game.getWidth(), game.getHeight());
        }

        // Determine if a region is already selected.
        Region selectedRegion = null;
        ArrayList<Region> regions = game.getAllRegions();
        for (Region region : regions) {
            if (region.isSelected()) {
                selectedRegion = region;
                break;
            }
        }
        
        //Calculates the mouses location
        Point frameLoc = game.getFrame().getLocation();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();
        Point mouse = MouseInfo.getPointerInfo().getLocation();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));

        // Render all regions and determine if a region is being hovered, if needed.
        for (Region region : regions) {
            if (selectedRegion == null && region.contains(mouse)) {
                selectedRegion = region;
            }
            else {
                g.drawImage(
                    region.getImage(), 0, 0,
                    game.getWidth(),
                    game.getHeight(),
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
                0, 0, game.getWidth(),
                game.getHeight(),
                0, 0, imageWidth,
                imageHeight,
                null
            );
        }
        
        printStats(g);

    }

    //Prints all of the Required Labels
    private void printStats(Graphics2D g)
    {
        Logic logic = game.getLogic();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        //Stats on Screen
        g.setFont(new Font("serif", Font.BOLD, locAdj(48,3)));
        g.drawString(logic.cashToString(),locAdj(10,1),locAdj(605,2));
        g.drawString(logic.shareToString(),locAdj(1000,1),locAdj(605,2));
        g.drawString(logic.getDate(),locAdj(1,1),locAdj(15,2));

        g.setFont(new Font("serif", Font.BOLD, locAdj(14,3)));
        g.setStroke(new BasicStroke(locAdj(2,3)));
        
        g.drawString("Product",locAdj(403,1),locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(376,1),locAdj(595,2),locAdj(logic.getProd(),3),locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(376,1),locAdj(595,2),locAdj(100,3),locAdj(20,3),locAdj(5,3),locAdj(5,3));

        g.drawString("Marketing",locAdj(545,1),locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(526,1),locAdj(595,2),locAdj(logic.getMark(),3),locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(526,1),locAdj(595,2),locAdj(100,3),locAdj(20,3),locAdj(5,3),locAdj(5,3));


        g.drawString("Logistics",locAdj(700,1),locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(676,1),locAdj(595,2),locAdj(logic.getLog(),3),locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(676,1),locAdj(595,2),locAdj(100,3),locAdj(20,3),locAdj(5,3),locAdj(5,3));
    }



    //Caries out the adjust for the drawing
    // Type 1 = X- axis
    // Type 2 = Y- axis
    // Type 3 = simple scaling
    private int locAdj(int location, int type){
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        if(type == 1)
            return (int)((location * scale) + margin.left);
        else if (type == 2)
            return (int)((location * scale) + margin.top + osAdj);
        else 
            return (int)((location * scale));
        
    }

    //Adjust the location of the text according to the OS interpretation of SWING
    private void osAdjust()
    {
        String os = game.getOS();

        if(os.toLowerCase().contains("mac")){
            osAdj = 0;
        }
        else
            osAdj = -15;
    }
}
