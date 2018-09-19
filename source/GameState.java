// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import escala.database.*;

public class GameState {

    public static enum Difficulty {
        EASY, NORMAL, HARD 
    }

    private int width;
    private int height;
    private int goalFPS;
    private double scale;
    private JFrame frame;
    private int frameHeight;
    private boolean isRunning;
    private Difficulty difficulty;
    private HashMap<String, Event> events;
    private HashMap<String, Region> regions;

    public GameState() throws SQLException, IOException {

        // Set frame size and other visual variables.
        scale = 1;
        frame = null;
        width = 1152;
        height = 648;
        goalFPS = 60;
        frameHeight = 47;

        // Set game status variables.
        isRunning = false;
        difficulty = Difficulty.NORMAL;

        // Load all regions and events from the database.
        //Portal portal = new Portal();
        //regions = portal.getAllRegions();
        //events = portal.getAllEvents();
        //portal.close();
        regions = new HashMap<>();
        events = new HashMap<>();
    }

    public int getWidth() {
        return (int) ((double) width * scale);
    }

    public int getHeight() {
        return (int) ((double) height * scale);
    }

    public double getScale() {
        return scale;
    }

    public boolean isGameRunning() {
        return isRunning;
    }

    public int getGoalFPS() {
        return goalFPS;
    }

    public long getFrameTime() {
        return (long) (1.0 / goalFPS * 1000.0);
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Region getRegion(String name) {
        return regions.get(name);
    }

    public ArrayList<Region> getAllRegions() {
        return new ArrayList<Region>(regions.values());
    }

    public Event getEvent(String name) {
        return events.get(name);
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void stopGame() {
        isRunning = false;
    }

    public void startGame() {
        isRunning = true;
    }

    public void setGoalFPS(int goalFPS) {
        this.goalFPS = goalFPS;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
