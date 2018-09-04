
package escala.database;

public class Region {
    private String name;
    private int entryCost;
    private int efficiencyCost;
    private int logisticsCost;
    private int marketingCost;
    private int taxRate;

    public Region(String name,
                  int entryCost,
                  int efficiencyCost,
                  int logisticsCost,
                  int marketingCost,
                  int taxRate) {
    
        this.name = name;
        this.entryCost = entryCost;
        this.efficiencyCost = efficiencyCost;
        this.logisticsCost = logisticsCost;
        this.marketingCost = marketingCost;
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public int getEntryCost() {
        return entryCost;
    }

    public int getEfficiencyCost() {
        return efficiencyCost;
    }

    public int getLogisticsCost() {
        return logisticsCost;
    }

    public int getMarketingCost() {
        return marketingCost;
    }

    public int getTaxRate() {
        return taxRate;
    }
}
