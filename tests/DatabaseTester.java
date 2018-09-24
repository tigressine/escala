package tests;

import escala.*;
import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseTester {
    @Test
    public void testPortalCanOpen() {
        try {
            Portal portal = new Portal();
        }
        catch (SQLException exception) {
            fail("Exception " + exception); 
        }
    }
}
