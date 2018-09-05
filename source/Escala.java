
package escala;

import escala.graphics.*;

/*
 * This class stores global variables and provides entry into the game.
 */

public class Escala {
	
	// Window Dimensions
	private static int width = 900;
	private static int height = 600;
	private static double SCALE = 1.5;
	public static int scaledWidth = (int) (width * SCALE);
	public static int scaledHeight = (int) (height * SCALE);
	
	// Game Variables
	private static boolean gameIsRunning = true;
	
	// Frame Rate
	public final static int GOAL_FPS = 60;
	public final static long FRAME_TIME = (long) (1.0 / GOAL_FPS * 1000.0);
	
	
	
	// Start Game
	public Escala() {
		Viewer gameViewer = new Viewer();
		gameViewer.run();
	}
	public static void main(String[] args) {
		Escala game = new Escala();
	}

	
	
	// Getters and Setters
	public static boolean gameIsRunning() {
		return gameIsRunning;
	}
	public static void setGameIsRunning(boolean isRunning) {
		Escala.gameIsRunning = isRunning;
	}
}
