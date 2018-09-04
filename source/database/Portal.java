package escala.database;

import java.sql.*;
import org.apache.derby.jdbc.*;

public class Portal {

    private Driver driver;
    private Connection connection;

    public Portal() throws SQLException {
        driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:derby:data/tables;create=true");

        String[] tables = {"regions", "events"};

        for (String table : tables) {
            DatabaseMetaData data = connection.getMetaData();
            ResultSet results = data.getTables(null, null, table.toUpperCase(), null);
            if (!results.next()) {
                System.out.println("creating table" + table);
                createTable(table);
            }
        }
    }

    private void createTable(String name) throws SQLException {
        Statement statement = connection.createStatement();

        if (name.equals("regions")) {
            statement.executeUpdate(
                "CREATE TABLE regions (" + 
                "name VARCHAR(15) PRIMARY KEY, " +
                "entryCost INT, efficiencyCost INT, " +
                "logisticsCost INT, " +
                "marketingCost INT, " +
                "taxRate INT)"
            );
        }
        else if (name.equals("events")) {
            statement.executeUpdate(
                "CREATE TABLE events (" +
                "name VARCHAR(15) PRIMARY KEY, " +
                "heading VARCHAR(15), description VARCHAR(300), " +
                "logisticsChange INT, marketingChange INT, " +
                "productChange INT, cashChange INT)"
            );
        }

        if (statement != null) {
            statement.close();
        }
    }
    
    public Region getRegion(String name) throws SQLException {
        Statement statement = connection.createStatement();
        String search = "SELECT * FROM regions WHERE name='" + name + "'";
        ResultSet results = statement.executeQuery(search);

        if (results.next() == false) {
            return null;
        }

        Region region = new Region(results.getString("name"),
                                   results.getInt("entryCost"),
                                   results.getInt("efficiencyCost"),
                                   results.getInt("logisticsCost"),
                                   results.getInt("marketingCost"),
                                   results.getInt("taxRate"));

        if (statement != null) {
            statement.close();
        }

        return region;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        } 
    }
}
