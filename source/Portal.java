// Part of Escala.
// Written by Tiger Sachse.

package escala;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import escala.structures.*;
import org.apache.derby.jdbc.*;

// A view into the main game database.
public class Portal {

    private Driver driver;
    private Connection connection;

    // Get all background images from file.
    public static HashMap<String, BufferedImage> getBackgrounds() throws IOException {
        HashMap<String, BufferedImage> backgrounds = new HashMap<>();
        String[] names = {
            "Normal",
            "Fast",
            "Paused"
        };

        for (String name : names) {
            URL url = Portal.class.getResource("/data/assets/Background" + name + ".png");
            String path = URLDecoder.decode(url.getPath(), "UTF-8");
        
            backgrounds.put(name, ImageIO.read(new File(path)));
        }

        return backgrounds;
    }

    // Create a new portal.
    public Portal() throws SQLException {

        // Create and register the driver and connection.
        driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:derby:data/tables");
    }

    // Return a map of all regions in the database.
    public HashMap<String, Region> getAllRegions() throws SQLException, IOException {
        HashMap<String, Region> regions = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM regions");

        // Add all the results to the map as new regions.
        while (results.next()) {
            Region region = new Region(
                results.getString("name"),
                results.getInt("centerX"),
                results.getInt("centerY"),
                results.getFloat("taxRate"),
                results.getFloat("worldShare"),
                results.getInt("entryCost"),
                results.getInt("logisticsCost"),
                results.getInt("marketingCost"),
                results.getInt("efficiencyCost")
            );
            regions.put(region.getName(), region); 
        }
            
        if (statement != null) {
            statement.close();
        }

        return regions;
    }

    // Get a random event from the database.
    public Event getRandomEvent(double minAlign, double maxAlign) {

        // Sanitize any wild inputs.
        if (minAlign < 0.0) {
            minAlign = 0.0;
        }
        if (maxAlign > 1.0) {
            maxAlign = 1.0;
        }

        // Build the query. I know this isn't the safest method to construct
        // an SQL statement. I like to live dangerously. ;)
        String query = String.format(
            "SELECT * FROM events WHERE alignment <= %f AND alignment >= %f " + 
            "ORDER BY RANDOM() { LIMIT 1 }",
            maxAlign, minAlign
        );

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
           
            // If an event was found, create that event object.
            Event event = null;
            if (results.next()) {
                event = new Event(
                    results.getString("name"),
                    results.getString("description"),
                    results.getFloat("alignment"),
                    results.getFloat("cashEffect"),
                    results.getInt("logisticsEffect"),
                    results.getInt("marketingEffect"),
                    results.getInt("efficiencyEffect")
                );
            }

            if (statement != null) {
                statement.close();
            }

            return event;
        }
        catch (SQLException exception) {
            return null;
        }
    }

    // Get a skill tree from the database.
    public SkillTree getSkillTree(String treeID) {
        SkillTree tree = new SkillTree(treeID);

        // BEGGING for an SQL injection... ;)
        String query = String.format(
            "SELECT * FROM skillNodes WHERE treeID = '%s'",
            treeID
        );

        try {
            Statement nodeStatement = connection.createStatement();
            ResultSet nodeResults = nodeStatement.executeQuery(query);

            // Retrieve all the skills from the database.
            while (nodeResults.next()) {
                Skill skill = new Skill(
                    nodeResults.getInt("nodeID"),
                    nodeResults.getString("name"),
                    nodeResults.getString("description"),
                    nodeResults.getFloat("cost"),
                    nodeResults.getInt("logisticsEffect"),
                    nodeResults.getInt("marketingEffect"),
                    nodeResults.getInt("efficiencyEffect")
                );

                query = String.format(
                    "SELECT followNode FROM skillEdges " +
                    " WHERE treeID = '%s' AND startNode = %d",
                    treeID,
                    skill.getID()
                );

                Statement edgeStatement = connection.createStatement();
                ResultSet edgeResults = edgeStatement.executeQuery(query);

                // Get all of this skill's children from the skillEdges table.
                HashSet<Integer> children = new HashSet<>();
                while(edgeResults.next()) {
                    children.add(edgeResults.getInt("followNode"));
                }

                // Add the skill to the skill tree with its children.
                tree.addSkill(skill, children);

                if (edgeStatement != null) {
                    edgeStatement.close();
                }
            }

            if (nodeStatement != null) {
                nodeStatement.close();
            }

            return tree;
        }
        catch (SQLException exception) {
            return null;
        }
    }

    // Close the connection.
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            } 
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }
    }
}
