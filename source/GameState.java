package escala;

import javax.swing.JFrame;

/* This Singleton class will store all the constants and global variables needed for the game to run.
 * 
 * TODO:  
 * 		we need to pre-define SCALE factors for the game:
 * 				SCALE  0.6  ->  960  x 540
 * 				SCALE  0.8  ->  1280 x 720  
 * 				SCALE  1.0	->  1600 x 900
 * 				SCALE  1.2  ->  1920 x 1080
 * 				Maximum size
 * 				full screen
 * 		prevent window from re-sizing???
 * */
public class GameState {
	
	//Singleton instance
	private static GameState instance = null;
	
	// Window Dimensions   images are 1152 x 648
	private int width = 1152;
	private int height = 648;
	private int frameHeight = 47;
	private double SCALE = 1.4;
		
	// Game Variables
	private boolean gameIsRunning = false;
	private boolean gameIsPaused = false;	//TODO::: set this while event handling
	private boolean gameIsActive = true;    //TODO::: 
	private String difficulty = "";
		
	// Window, Frame, and Canvas related variables
	private int GOAL_FPS = 60;
	private static JFrame frame = null;
	
	
	// CONSTRUCTOR
	private GameState(){
		// Exists only to defeat instantiation.
		// LEAVE THIS EMPTY
	}
	
	public static GameState getInstance() {
		if(instance == null){
			instance = new GameState();
		}
		return instance;
	}
	
	// GETTERS AND SETTERS
	// Width:  no set method
	public int getWidth(){
		return (int) ((double) width * SCALE);
	}
	
	// Height:  no set method
	public int getHeight(){
		return (int) ((double) height * SCALE);
	}
	
	// Scale
	public double getScale(){
		return SCALE;
	}
	public void setScale(double scale){
		//TODO: sanity check
		SCALE = scale;
	}
	
	// GameIsRunning 
	public boolean gameIsRunning(){
		return gameIsRunning;
	}
	public void stopGame(){
		gameIsRunning = false;
	}
	
	// Goal FPS
	public int getGoalFPS(){
		return GOAL_FPS;
	}
	public void setGoalFPS(int fps){
		//TODO: sanity check 
		GOAL_FPS = fps;
	}
	
	// frame time (in milliseconds)
	public long getFrameTime(){
		return (long) (1.0 / GOAL_FPS * 1000.0);
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public JFrame getFrame() {
		if(frame == null)
			frame = new JFrame();
		return frame;
	}
	
	public void setFrame(JFrame jf){
		frame = jf;
	}

	public void setDifficulty(String diff) {
		difficulty = diff;
	}
	
	public String getDifficulty(){
		return difficulty;
	}

	public void setGameIsRunning(boolean b) {
		gameIsRunning = b;
	}
	
	
}
