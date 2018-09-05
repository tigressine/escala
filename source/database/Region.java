// Part of Escala by Tiger Sachse.

package escala.database;

// Read-only Region data structure.
public class Region {
    private String name;
    private float taxRate;
    private float entryCost;
    private int logisticsCost;
    private int marketingCost;
    private int efficiencyCost;

    // Create a new region.
    public Region(String name,
                  float taxRate,
                  float entryCost,
                  int logisticsCost,
                  int marketingCost,
                  int efficiencyCost) {
    
        this.name = name;
        this.taxRate = taxRate;
        this.entryCost = entryCost;
        this.marketingCost = marketingCost;
        this.logisticsCost = logisticsCost;
        this.efficiencyCost = efficiencyCost;
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
}
