// Part of Escala by Tiger Sachse.

package escala.database;

// Read-only Event data structure.
public class Event {

    private String name;
    private String description;
    private float alignment;
    private float cashEffect;
    private int productEffect;
    private int marketingEffect;
    private int logisticsEffect;

    // Create a new event.
    public Event(String name,
                 String description,
                 float alignment,
                 float cashEffect,
                 int productEffect,
                 int marketingEffect,
                 int logisticsEffect) {
    
        this.name = name;
        this.description = description;
        this.alignment = alignment;
        this.cashEffect = cashEffect;
        this.productEffect = productEffect;
        this.marketingEffect = marketingEffect;
        this.logisticsEffect = logisticsEffect;
    }

    // Get the name of this event.
    public String getName() {
        return name;
    }

    // Get the description of this event.
    public String getDescription() {
        return description;
    }

    // Get the good-bad alignment of this event.
    public float getAlignment() {
        return alignment;
    }

    // Get the cash effect of this event.
    public float getCashEffect() {
        return cashEffect;
    }

    // Get the product effect of this event.
    public int getProductEffect() {
        return productEffect;
    }

    // Get the marketing effect of this event.
    public int getMarketingEffect() {
        return marketingEffect;
    }

    // Get the logisitics effect of this event.
    public int getLogisticsEffect() {
        return logisticsEffect;
    }
}
