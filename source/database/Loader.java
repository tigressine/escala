// Part of Escala.
// Written by Tiger Sachse.

package escala.database;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;
import org.apache.derby.jdbc.*;

public class Loader {

    private Driver driver;
    private Connection connection;

    // Create a new loader.
    public Loader() throws SQLException {

        // Create and register the driver and connection.
        driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:derby:data/tables;create=true");
    }

    public void createMissingTables() throws SQLException {
        String[] tables = {"regions", "positiveEvents", "negativeEvents"};
        DatabaseMetaData data = connection.getMetaData();
        for (String table : tables) {
            ResultSet results = data.getTables(null, null, table.toUpperCase(), null);
            if (!results.next()) {
                createTable(table);
            }
        }
    }
    
    public void createRegions() throws UnsupportedEncodingException, SQLException, IOException {
        String[] regions = {
            "Asia", "EasternEurope", "LatinAmerica",
            "MiddleEast", "NorthAfrica", "NorthAmerica",
            "Oceania", "SouthAfrica", "SouthAmerica",
            "WesternEurope"
        };

        for (String region : regions) {
            URL url = getClass().getResource("/data/assets/Background.png");
            String path = URLDecoder.decode(url.getPath(), "UTF-8");
            addRegion(path);     
        }
    }

    // Create a new table in the database.
    private void createTable(String name) throws SQLException {
        Statement statement = connection.createStatement();

        if (name.equals("regions")) {
            statement.executeUpdate(
                "CREATE TABLE regions (" + 
                "name VARCHAR(15) PRIMARY KEY, " +
                "taxRate REAL, " +
                "entryCost REAL, " +
                "logisticsCost INT, " +
                "marketingCost INT, " +
                "efficiencyCost INT)"
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
        }

        if (statement != null) {
            statement.close();
        }
    }

    // Add a new region to the database.
    private void addRegion(String filename) throws SQLException, IOException {
        Scanner scanner = new Scanner(new File(filename));

        String name = scanner.nextLine();
        float taxRate = scanner.nextFloat();
        float entryCost = scanner.nextFloat();
        int logisticsCost = scanner.nextInt();
        int marketingCost = scanner.nextInt();
        int efficiencyCost = scanner.nextInt();

        Statement statement = connection.createStatement();
        statement.execute(String.format("DELETE FROM regions WHERE name='%s'", name));
        statement.execute(String.format(
            "INSERT INTO regions VALUES ('%s', %f, %f, %d, %d, %d)",
            name,
            taxRate,
            entryCost,
            logisticsCost,
            marketingCost,
            efficiencyCost
        ));
       
        if (statement != null) {
            statement.close();
        }

        scanner.close();
    }

    // Close the connection.
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        } 
    }
}
