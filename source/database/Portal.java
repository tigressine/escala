package escala.database;

import java.sql.*;
import org.apache.derby.jdbc.*;

public class Portal {
    public void printMe() throws SQLException {
        Driver driver = new EmbeddedDriver();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection("jdbc:derby:data/regions;create=true");
    }    

}
