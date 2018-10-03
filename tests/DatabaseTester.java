// Part of Escala.
// Written by Tiger Sachse.

package tests;

import escala.*;
import java.io.*;
import java.sql.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

// Test the database and database portal.
public class DatabaseTester {

    // Ensure that the portal can open and close.
    @Test
    public void testNewPortal() {
        try {
            Portal portal = new Portal();
            portal.close();
        }
        catch (SQLException exception) {
            fail("Exception " + exception); 
        }
    }

    // Ensure that all regions can be fetched from the portal.
    @Test
    public void testGetAllRegions() {
        try {
            Portal portal = new Portal();
            HashMap<String, Region> regions = portal.getAllRegions();

            assertNotNull(regions);
            assertEquals(10, regions.size());

            for (Map.Entry<String, Region> entry : regions.entrySet()) {
                assertEquals(entry.getKey(), entry.getValue().getName());
            }

            portal.close();
        }
        catch (SQLException | IOException exception) {
            fail("Exception " + exception);
        }
    }

    // Ensure that appropriate random events can be retrieved from the portal.
    @Test
    public void testGetRandomEvent() {
        Portal portal = null;
        try {
            portal = new Portal();
        }
        catch (SQLException exception) {
            fail("Exception " + exception);
        }

        // Retrieve 500 random events from the portal. Each event must have
        // an alignment between the two random floats randLow and randHigh.
        for (int test = 0; test < 500; test++) {
            double randLow = Math.random() * .7;
            double randHigh = randLow + (Math.random() * (1.0 - randLow));
            Event event = portal.getRandomEvent(randLow, randHigh); 

            // Not all alignment parameters will result in an event. Only
            // perform checks for alignment parameters that returned an event.
            if (event != null) {
                double eventAlignment = event.getAlignment();
                assertTrue(eventAlignment >= randLow && eventAlignment <= randHigh);
            }
        }

        portal.close();
    }

    // Ensure that getRandomEvent can handle wild bounds.
    @Test
    public void testRandomEventWithWildBounds() {
        Portal portal = null;
        try {
            portal = new Portal();
        }
        catch (SQLException exception) {
            fail("Exception " + exception);
        }
   
        Event event = portal.getRandomEvent(-1.0, .9);
        double eventAlignment = event.getAlignment();
        assertTrue(eventAlignment >= 0 && eventAlignment <= 1);
        
        event = portal.getRandomEvent(.1, 200.0);
        eventAlignment = event.getAlignment();
        assertTrue(eventAlignment >= 0 && eventAlignment <= 1);
        
        event = portal.getRandomEvent(1.0, 0.0);
        assertNull(event);
    }
}
