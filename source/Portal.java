// Part of Escala.
// Written by Tiger Sachse.

package escala;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.*;

// A view into the main game database.
public class Portal {

    private Driver driver;
    private Connection connection;

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
                results.getFloat("taxRate"),
                results.getFloat("entryCost"),
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

    public Event getRandomEvent() {
        return getRandomEvent(0.0, 1.0);
    }

    public Event getRandomPositiveEvent() {
        return getRandomEvent(0.55, 1.0);
    }

    public Event getRandomNegativeEvent() {
        return getRandomEvent(0.0, 0.45);
    }

    public Event getRandomEvent(double minAlign, double maxAlign) {
        if (minAlign < 0.0) {
            minAlign = 0.0;
        }
        if (maxAlign > 1.0) {
            maxAlign = 1.0;
        }

        String query = String.format(
            "SELECT * FROM events WHERE alignment <= %f AND alignment >= %f " + 
            "ORDER BY RANDOM() { LIMIT 1 }",
            maxAlign, minAlign
        );

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            if (statement != null) {
                statement.close();
            }

            if (results.next()) {
                return new Event(
                    results.getString("name"),
                    results.getString("description"),
                    results.getFloat("alignment"),
                    results.getFloat("cashEffect"),
                    results.getInt("logisticsEffect"),
                    results.getInt("marketingEffect"),
                    results.getInt("productEffect")
                );
            }
        }
        catch (SQLException exception) {
            return null;
        }

        return null;
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
