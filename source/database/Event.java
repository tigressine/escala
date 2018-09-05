// Part of Escala by Tiger Sachse.

package escala.database;

// Read-only Event data structure.
public class Event {

    private String name;
    private String description;
    private float alignment;
    private float cashEffect;
    private int marketingEffect;
    private int logisticsEffect;
    private int efficiencyEffect;

    // Create a new event.
    public Event(String name,
                 String description,
                 float alignment,
                 float cashEffect,
                 int marketingEffect,
                 int logisticsEffect,
                 int efficiencyEffect) {
    
        this.name = name;
        this.description = description;
        this.alignment = alignment;
        this.cashEffect = cashEffect;
        this.marketingEffect = marketingEffect;
        this.logisticsEffect = logisticsEffect;
        this.efficiencyEffect = efficiencyEffect;
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

    // Get the efficiency effect of this event.
    public int getEfficiencyEffect() {
        return efficiencyEffect;
    }

    // Get the marketing effect of this event.
    public int getMarketingEffect() {
        return marketingEffect;
    }

    // Get the logisitics effect of this event.
    public int getLogisticsEffect() {
        return logisticsEffect;
    }
    
    // Create a string representation of this event.
    public String toString() {
        return String.format(
            "Event \"%s\":\n" +
            "-> Alignment: %.3f\n" +
            "-> Cash Effect: %.4f\n" +
            "-> Logistics Effect: %d\n" +
            "-> Marketing Effect: %d\n" +
            "-> Efficiency Effect: %d",
            name,
            alignment,
            cashEffect,
            logisticsEffect,
            marketingEffect,
            efficiencyEffect
        );
    }
}
