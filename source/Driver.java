package escala;

import java.sql.*;
import escala.database.Portal;

public class Driver {
    public static void main(String[] args) throws SQLException {
        Portal portal = new Portal();
        portal.printMe();
    }
}
