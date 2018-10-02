// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala;

import escala.*;
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
    private Portal portal;
    private int frameHeight;
    private boolean isRunning;
    private boolean isPaused;
    private int gameSpeed = 1; // days per second (default is 1)
    private Difficulty difficulty;
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
        isPaused = false;
        difficulty = Difficulty.NORMAL;

        // Load the database and get all regions.
        portal = new Portal();
        regions = portal.getAllRegions();
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
    public boolean gameIsRunning() {
        return isRunning;
    }

    // Check if game is paused.
    public boolean gameIsPaused() {
        return isPaused;
    }

    // Get game speed
    public int getGameSpeed(){
        return gameSpeed;
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

    // Get a random event.
    public Event getRandomEvent() {
        return portal.getRandomEvent(0.0, 1.0);
    }

    // Get a random positive event.
    public Event getRandomPositiveEvent() {
        return portal.getRandomEvent(0.55, 1.0);
    }

    // Get a random negative event.
    public Event getRandomNegativeEvent() {
        return portal.getRandomEvent(0.0, 0.45);
    }

    // Get a random event with a specific alignment interval.
    public Event getRandomEvent(double minAlign, double maxAlign) {
        return portal.getRandomEvent(minAlign, maxAlign);
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

    // Set the game to paused.
    public void pauseGame(){
        isPaused = true;
    }

    // Set the game to unpaused.
    public void continueGame(){
        isPaused = false;
    }

    // Set game speed
    public void setGameSpeed(int gameSpeed){
        this.gameSpeed = gameSpeed;
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

    // Close the database portal.
    public void closePortal() {
        portal.close();
    }
}
