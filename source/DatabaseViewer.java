// Part of Escala.
// Written by Tiger Sachse.

package escala;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.*;

// Enable a view into the main game database.
public class DatabaseViewer {

    private Driver driver;
    private Connection connection;

    // Create a new portal.
    public DatabaseViewer() throws SQLException {

        // Create and register the driver and connection.
        driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:derby:data/tables");
    }

    public HashMap<String, Event> getAllEvents() {
        return null;
    }

    // Get all regions from the database.
    public HashMap<String, Region> getAllRegions() throws SQLException, IOException {
        HashMap<String, Region> regions = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM regions");

        while (results.next()) {
            Region region = new Region(results.getString("name"),
                                       results.getFloat("taxRate"),
                                       results.getFloat("entryCost"),
                                       results.getInt("logisticsCost"),
                                       results.getInt("marketingCost"),
                                       results.getInt("efficiencyCost"));
            regions.put(region.getName(), region); 
        }
            
        if (statement != null) {
            statement.close();
        }

        return regions;
    }

    // Close the connection.
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        } 
    }
}
