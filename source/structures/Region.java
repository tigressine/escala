// Part of Escala.
// Written by Tiger Sachse.

package escala.structures;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;

// Read-only Region data structure.
public class Region {
    private String name;
    private Point center;
    private float taxRate;
    private int entryCost;
    private Polygon polygon;
    private boolean selected;
    private boolean purchased;
    private int logisticsCost;
    private int marketingCost;
    private double worldShare;
    private int efficiencyCost;
    private double marketShare;
    private BufferedImage image;
    private BufferedImage outline;

    // Create a new region.
    public Region(
        String name,
        int centerX,
        int centerY,
        float taxRate,
        double worldShare,
        int entryCost,
        int logisticsCost,
        int marketingCost,
        int efficiencyCost
        ) throws IOException {
   
        this.marketShare = 0;
        this.selected = false;
        this.purchased = false;

        this.name = name;
        this.taxRate = taxRate;
        this.entryCost = entryCost;
        this.worldShare = worldShare;
        this.marketingCost = marketingCost;
        this.logisticsCost = logisticsCost;
        this.efficiencyCost = efficiencyCost;
        this.center = new Point(centerX, centerY);

        loadImage();
        loadOutline();
        loadPolygon();
    }

    // Get the center location of the Region.
    public Point getCenter() {
        return center;
    }

    // Get the name of this region.
    public String getName() {
        return name;
    }
    
    // Return the current market share of the region.
    public double getMarketShare() {
        return marketShare;
    }

    // Get the world share for this region.
    public double getWorldShare() {
        return worldShare;
    }

    // Get the tax rate in this region.
    public float getTaxRate() {
        return taxRate;
    }

    // Get the entry cost to access this region.
    public int getEntryCost() {
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

    // Check if the region has been selected.
    public boolean isSelected() {
        return selected;
    }

    // Check if the region has been purchased.
    public boolean isPurchased() {
        return purchased;
    }

    // Check if a point is contained within this region's polygon.
    public boolean contains(Point point) {
        return polygon.contains(point);
	}

    // Add market growth to the region's market share.
    public void incrementMarketShare(double amount) {
        marketShare += amount;
    }

    // Set the market share of the region.
    public void setMarketShare(double marketShare) {
        this.marketShare = marketShare;
    }

    // Select the region.
    public void select() {
        selected = true;
    }

    // Deselect the region.
    public void deselect() {
        selected = false;
    }

    // Purchase the region.
    public void purchase() {
        purchased = true;
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
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        image = ImageIO.read(new File(path));
    }

    // Load the associated outline from file.
    private void loadOutline() throws IOException {
        URL url = getClass().getResource("/data/assets/" + name + "Glow.png");
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        outline = ImageIO.read(new File(path));
    }

    // Load the polygon information from file.
    private void loadPolygon() throws IOException {
        polygon = new Polygon();

        URL url = getClass().getResource("/data/polygons/" + name + ".data"); 
        String path = URLDecoder.decode(url.getPath(), "UTF-8");
        Scanner scanner = new Scanner(new File(path));

        while (scanner.hasNext()) {
            polygon.addPoint(scanner.nextInt(), scanner.nextInt());
        }
    }
}
