// Part of Escala by Tiger Sachse.

package escala.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.*;

// Enable IO with the main game database.
public class Portal {

    private Driver driver;
    private Connection connection;

    // Create a new portal.
    public Portal() throws SQLException {

        // Create and register the driver and connection.
        driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:derby:data/tables;create=true");

        // Create any missing tables.
        String[] tables = {"regions", "positiveEvents", "negativeEvents"};
        DatabaseMetaData data = connection.getMetaData();
        for (String table : tables) {
            ResultSet results = data.getTables(null, null, table.toUpperCase(), null);
            if (!results.next()) {
                createTable(table);
            }
        }
    }

    // Create a new table in the database.
    private void createTable(String name) throws SQLException {
        Statement statement = connection.createStatement();

        if (name.equals("regions")) {
            statement.executeUpdate(
                "CREATE TABLE regions (" + 
                "name VARCHAR(15) PRIMARY KEY, " +
                "entryCost REAL, efficiencyCost INT, " +
                "logisticsCost INT, " +
                "marketingCost INT, " +
                "taxRate REAL)"
            );
        }
        else if (name.equals("positiveEvents")) {
            statement.executeUpdate(
                "CREATE TABLE positiveEvents (" +
                "name VARCHAR(15) PRIMARY KEY, " +
                "description VARCHAR(300), alignment REAL, " +
                "logisticsEffect INT, marketingEffect INT, " +
                "productEffect INT, cashEffect REAL)"
            );
        }
        else if (name.equals("negativeEvents")) {
            statement.executeUpdate(
                "CREATE TABLE negativeEvents (" +
                "name VARCHAR(15) PRIMARY KEY, " +
                "description VARCHAR(300), alignment REAL, " +
                "logisticsEffect INT, marketingEffect INT, " +
                "productEffect INT, cashEffect REAL)"
            );

        if (statement != null) {
            statement.close();
        }
    }
   
    // Add a new region to the database.
    public void addRegion(String filename) throws SQLException, IOException {
    
    }

    // Add a new event to the database.
    public void addEvent(String filename) throws SQLException, IOException {
    
    }

    // Get a named region from the database.
    public Region getRegion(String name) throws SQLException {
        Statement statement = connection.createStatement();
        String search = "SELECT * FROM regions WHERE name='" + name + "'";
        ResultSet results = statement.executeQuery(search);

        if (results.next() == false) {
            return null;
        }

        // Create a new region from the results.
        Region region = new Region(results.getString("name"),
                                   results.getFloat("taxRate"),
                                   results.getFloat("entryCost"),
                                   results.getInt("logisticsCost"),
                                   results.getInt("marketingCost"),
                                   results.getInt("efficiencyCost"));

        if (statement != null) {
            statement.close();
        }

        return region;
    }

    // Get all regions from the database.
    public ArrayList<Region> getAllRegions() throws SQLException {
    
    }

    // Get an event from the database by name.
    public Event getEvent(String name) throws SQLException {
    
    }

    // Get a random event in between the minimum and maximum alignment values.
    public Event getRandomEvent(float minAlignment, float maxAlignment) throws SQLException {
        
    }

    // Get a random positive event (with an alignment above .5).
    public Event getRandomPositiveEvent(float maxAlignment) throws SQLException {
        return getRandomEvent(.5f, maxAlignment);
    }

    // Get a random negative event (with an alignment below .5).
    public Event getRandomNegativeEvent(float minAlignment) throws SQLException {
        return getRandomEvent(minAlignment, .5f);
    }

    // Close the connection.
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        } 
    }
}
