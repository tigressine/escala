// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

// Stores the game status and many variables.
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

    // Create a new game state with some defaults.
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
        DatabaseViewer viewer = new DatabaseViewer();
        regions = viewer.getAllRegions();
        events = viewer.getAllEvents();
        viewer.close();
    }

    // Get the game width.
    public int getWidth() {
        return (int) ((double) width * scale);
    }

    // Get the game height.
    public int getHeight() {
        return (int) ((double) height * scale);
    }

    // Get the resolution scale of the window.
    public double getScale() {
        return scale;
    }

    // Check if the game is running.
    public boolean isGameRunning() {
        return isRunning;
    }

    // Get the goal FPS.
    public int getGoalFPS() {
        return goalFPS;
    }

    // Get the frame time.
    public long getFrameTime() {
        return (long) (1.0 / goalFPS * 1000.0);
    }

    // Get the height of a frame.
    public int getFrameHeight() {
        return frameHeight;
    }

    // Get the game frame.
    public JFrame getFrame() {
        return frame;
    }

    // Get the difficulty of the present frame.
    public Difficulty getDifficulty() {
        return difficulty;
    }

    // Get a region by name from loaded data.
    public Region getRegion(String name) {
        return regions.get(name);
    }

    // Get a list of all regions.
    public ArrayList<Region> getAllRegions() {
        return new ArrayList<Region>(regions.values());
    }

    // Get an event by name from loaded data.
    public Event getEvent(String name) {
        return events.get(name);
    }

    // Set the scale of the window.
    public void setScale(double scale) {
        this.scale = scale;
    }

    // Set the game running flag to false.
    public void stopGame() {
        isRunning = false;
    }

    // Set the game running flag to true.
    public void startGame() {
        isRunning = true;
    }

    // Set the goal FPS.
    public void setGoalFPS(int goalFPS) {
        this.goalFPS = goalFPS;
    }

    // Set the current frame of the game.
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    // Set the difficulty of the game.
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
