// Part of Escala by Tiger Sachse.

package escala.database;

import java.io.*;
import java.net.*;
import javax.imageio.*;
import java.awt.image.*;

// Read-only Region data structure.
public class Region {
    private String name;
    private float taxRate;
    private float entryCost;
    private int logisticsCost;
    private int marketingCost;
    private int efficiencyCost;
    private BufferedImage image;
    private BufferedImage outline;

    // Create a new region.
    public Region(String name,
                  float taxRate,
                  float entryCost,
                  int logisticsCost,
                  int marketingCost,
                  int efficiencyCost) throws IOException {
    
        this.name = name;
        this.taxRate = taxRate;
        this.entryCost = entryCost;
        this.marketingCost = marketingCost;
        this.logisticsCost = logisticsCost;
        this.efficiencyCost = efficiencyCost;
        loadImage();
        loadOutline();
    }

    // Get the name of this region.
    public String getName() {
        return name;
    }
    
    // Get the tax rate in this region.
    public float getTaxRate() {
        return taxRate;
    }

    // Get the entry cost to access this region.
    public float getEntryCost() {
        return entryCost;
    }

    // Get the logistics cost to access this region.
    public int getLogisticsCost() {
        return logisticsCost;
    }

    // Get the marketing cost to access this region.
    public int getMarketingCost() {
        return marketingCost;
    }

    // Get the efficiency cost to access this region.
    public int getEfficiencyCost() {
        return efficiencyCost;
    }

    // Get the associated image for this region.
    public BufferedImage getImage() {
        return image;
    }

    // Get the associated image outline for this region.
    public BufferedImage getOutline() {
        return outline;
    }

    // Create a string representation of this region.
    public String toString() {
        return String.format(
            "Region \"%s\":\n" +
            "-> Tax Rate: %.3f\n" +
            "-> Entry Cost: %.4f\n" +
            "-> Logistics Cost: %d\n" +
            "-> Marketing Cost: %d\n" +
            "-> Efficiency Cost: %d",
            name,
            taxRate,
            entryCost,
            logisticsCost,
            marketingCost,
            efficiencyCost
        );
    }

    // Load the associated image from file.
    private void loadImage() throws IOException {
        URL url = getClass().getResource("/data/assets/" + name + ".png");
        image = ImageIO.read(new File(url.getPath()));
    }

    // Load the associated outline from file.
    private void loadOutline() throws IOException {
        URL url = getClass().getResource("/data/assets/" + name + "Glow.png");
        outline = ImageIO.read(new File(url.getPath()));
    }
}
