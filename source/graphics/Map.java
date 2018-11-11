// Part of Escala.
// Written by Spencer Phillips, Tiger Sachse and Jonathan Ponader.

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

        // Render all regions and determine if a region is being hovered, if needed.
        for (Region region : regions) {
            if (selectedRegion == null && region.contains(Mouse()) && !region.isPurchased()) {
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
                if(region.isPurchased())
                    drawBought(region, g); 
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

            if(selectedRegion.isSelected() && !selectedRegion.isPurchased())
                drawBuy(selectedRegion, g);
            else if(selectedRegion.isSelected() && selectedRegion.isPurchased())
                drawBought(selectedRegion, g);
        }
        printStats(g);
    }

    // Handles calculating the mouse position for the hover 
    private Point Mouse(){
        Point frameLoc = game.getFrame().getLocation();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();
        Point mouse = MouseInfo.getPointerInfo().getLocation();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));

        return mouse;
    }

    // Prints all of the Required Labels
    private void printStats(Graphics2D g)
    {
        Logic logic = game.getLogic();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        //Stats on Screen
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, locAdj(48,3)));
        g.drawString(logic.cashToString(), locAdj(10,1), locAdj(605,2));
        g.drawString(logic.shareToString(), locAdj(975,1), locAdj(605,2));
        g.drawString(logic.getDate(), locAdj(1,1), locAdj(15,2));

        g.setFont(new Font("serif", Font.BOLD, locAdj(14,3)));
        g.setStroke(new BasicStroke(locAdj(2,3)));
        
        g.drawString("Product", locAdj(403,1), locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(376,1), locAdj(595,2), locAdj(logic.getProd(),3), locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(376,1), locAdj(595,2), locAdj(100,3), locAdj(20,3), locAdj(5,3), locAdj(5,3));

        g.drawString("Marketing", locAdj(545,1), locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(526,1), locAdj(595,2), locAdj(logic.getMark(),3), locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(526,1), locAdj(595,2), locAdj(100,3), locAdj(20,3), locAdj(5,3), locAdj(5,3));

        g.drawString("Logistics", locAdj(700,1), locAdj(590,2));
        g.setColor(Color.YELLOW);
        g.fillRect(locAdj(676,1), locAdj(595,2), locAdj(logic.getLog(),3), locAdj(20,3));
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(676,1), locAdj(595,2), locAdj(100,3), locAdj(20,3), locAdj(5,3), locAdj(5,3));
    }



    // Caries out the adjust for the drawing
    // Type 1 = X- axis
    // Type 2 = Y- axis
    // Type 3 = simple scaling
    public int locAdj(int location, int type){
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        if(type == 1)
            return (int)((location * scale) + margin.left);
        else if (type == 2)
            return (int)((location * scale) + margin.top + osAdj);
        else 
            return (int)((location * scale)); 
    }

    // Adjust the location of the text according to the OS interpretation of SWING
    private void osAdjust()
    {
        String os = game.getOS();

        if(os.toLowerCase().contains("mac")){
            osAdj = 0;
        }
        else
            osAdj = -15;
    }

    // Draws the Box to Buy the region
    private void drawBuy(Region region, Graphics2D g)
    {
        Point w = region.getCenter();
        Point p = new Point(w);
        p.y = p.y - 40;

        g.setStroke(new BasicStroke(locAdj(2,3)));
        g.setColor(Color.GRAY);
        g.fillRoundRect(locAdj(p.x - 60,1), locAdj(p.y + 40 ,2), locAdj(190,3), locAdj(35,3), locAdj(15,3), locAdj(15,3));
        
        // Changes Color on hover
        if(new Rectangle(locAdj(p.x,1), locAdj(p.y,2), locAdj(70,3), locAdj(40,3)).contains(Mouse()))
            g.setColor(Color.DARK_GRAY);
        
        g.fillRoundRect(locAdj(p.x,1), locAdj(p.y,2), locAdj(70,3), locAdj(40,3), locAdj(15,3), locAdj(15,3));
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, locAdj(25,3)));
        g.drawString("BUY",locAdj(p.x+10,1), locAdj(p.y+27,2));
        g.setFont(new Font("serif", Font.BOLD, locAdj(14,3)));
        g.drawString("Cost", locAdj(p.x-58,1), locAdj(p.y+55,2));
        g.drawString(String.format("%d", region.getEntryCost()), locAdj(p.x-55,1), locAdj(p.y+70,2));
        g.drawString("Prod", locAdj(p.x,1), locAdj(p.y+55,2));
        g.drawString(String.format("%d", region.getEfficiencyCost()), locAdj(p.x+5,1), locAdj(p.y+70,2));
        g.drawString("Mark", locAdj(p.x+49,1), locAdj(p.y+55,2));
        g.drawString(String.format("%d", region.getMarketingCost()), locAdj(p.x+55,1), locAdj(p.y+70,2));
        g.drawString("Log", locAdj(p.x+105,1), locAdj(p.y+55,2));
        g.drawString(String.format("%d", region.getLogisticsCost()), locAdj(p.x+105,1), locAdj(p.y+70,2));
        g.drawRoundRect(locAdj(p.x,1), locAdj(p.y,2), locAdj(70,3), locAdj(40,3), locAdj(15,3), locAdj(15,3));
        g.drawRoundRect(locAdj(p.x - 60,1), locAdj(p.y + 40 ,2), locAdj(190,3), locAdj(35,3), locAdj(15,3), locAdj(15,3));
    }

    // Draws the PieChart showing that a region is owned
    private void drawBought(Region region, Graphics2D g)
    {
        Point p = region.getCenter();

        g.setStroke(new BasicStroke(locAdj(2,3)));
        g.setColor(Color.GRAY);
        g.fillRoundRect(locAdj(p.x-30,1), locAdj(p.y - 25,2), locAdj(100,3), locAdj(70,3), locAdj(15,3), locAdj(15,3));
        
        g.setColor(Color.BLACK);
        g.drawRoundRect(locAdj(p.x-30,1), locAdj(p.y - 25,2), locAdj(100,3), locAdj(70,3), locAdj(15,3), locAdj(15,3));

        PieChart pc = new PieChart();
        pc.paint(g, region.getCenter(), (int)region.getMarketShare());

        g.setFont(new Font("serif", Font.BOLD, locAdj(20,3)));
        g.drawString("Purchased", locAdj(p.x - 23 ,1), locAdj(p.y + 33,2));
    }
}
