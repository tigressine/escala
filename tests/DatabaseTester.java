import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseTester {
    public void testPortalCanOpen() {
        try {
            Portal portal = new Portal();
        }
        catch (SQLException exception) {
            fail("Exception " + exception); 
        }
    }
}
