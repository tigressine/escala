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

    private int imageWidth;
    private int imageHeight;

    //Barath for Testing of Game timer
    Integer time = 0;

    private Game game;

    // Create a new map.
    public Map(Game game) {
        imageWidth = game.getWidth();
        imageHeight =  game.getHeight();
        this.game = game;
    }

    // Render the map onto the screen.
    public void renderMap(Graphics2D g) 
    {
        // Load the background and render it.
        BufferedImage background = game.getBackground();
        System.out.println(game.getGameSpeed());
        System.out.println(game.getWidth());
        System.out.println(game.getHeight());
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

        Point mouse = MouseInfo.getPointerInfo().getLocation();

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
        
        Logic logic = Logic.getInstance();
        Point frameLoc = game.getFrame().getLocation();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));

        //Stats on Screen
        g.setFont(new Font("serif", Font.BOLD, (int)(48 * scale)));
        g.drawString(logic.cashToString(),(int)((10 * scale) + margin.left),(int)((605 * scale) + margin.top));
        g.drawString(logic.shareToString(),(int)((1000 * scale) + margin.left),(int)((605 * scale)+ margin.top));

        g.drawString(time.toString(),(int)((15 * scale) + margin.left),(int)((15 * scale) + margin.top));

        g.setFont(new Font("serif", Font.BOLD, (int)(14 * scale)));
        g.setStroke(new BasicStroke((int)(2 * scale)));
        
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
