// Part of Escala.
// Written by Tiger Sachse.

package escala;

// Holds information about a skill in a skill tree.
public class Skill {
    private int id;
    private String name;
    private double cost;
    private String description;
    private int logisticsEffect;
    private int marketingEffect;
    private int efficiencyEffect;

    // Construct a new skill.
    public Skill(
        int id,
        String name,
        String description,
        double cost,
        int logisticsEffect,
        int marketingEffect,
        int efficiencyEffect) {

        this.id = id;
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.marketingEffect = marketingEffect;
        this.logisticsEffect = logisticsEffect;
        this.efficiencyEffect = efficiencyEffect;
    }

    // Get the ID of this skill.
    public int getID() {
        return id;
    }

    // Return a string holding this skill's information.
    public String toString() {
        StringBuilder builder = new StringBuilder();
    
        builder.append(String.format("id: %d\n", id));
        builder.append(String.format("name: %s\n", name));
        builder.append(String.format("description: %s\n", description));
        builder.append(String.format("cost: %f\n", cost));
        builder.append(String.format("logistics: %d\n", logisticsEffect));
        builder.append(String.format("marketing: %d\n", marketingEffect));
        builder.append(String.format("efficiency: %d", efficiencyEffect));

        return builder.toString();
    }
}
