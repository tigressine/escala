package escala;

import java.sql.*;
import escala.database.*;

public class Driver {
    public static void main(String[] args) throws SQLException {
        Portal portal = new Portal();
        Region region1 = portal.getRegion("USA");
    }
}
