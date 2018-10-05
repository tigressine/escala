
package escala;

public class Skill {
    private int id;
    private String name;
    private double cost;
    private String description;
    private int logisticsEffect;
    private int marketingEffect;
    private int efficiencyEffect;

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

    public int getID() {
        return id;
    }
}
