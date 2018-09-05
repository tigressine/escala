import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/*
 * This class will create the window and start the game
 * 
 * 
 * Take a look at the run() function, don't worry about anything else yet...
 * NOTE:  Spencer will be making EXTREME edits to this class over the next couple of days...
 */

public class Escala {
	private JFrame frame;
	private Canvas canvas;
	
	//Double Buffer Graphics Configuration
	private GraphicsConfiguration config = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	private BufferStrategy strategy;
	private Graphics2D graphics;
	private Graphics2D backGraphics;
	
	//Background Image
	private BufferedImage backgroundImage;
	
	//window Size Variables
	//TODO::: pay attention to aspect ratios of different screens
	private static int width = 900;
	private static int height = 600;
	private final static double SCALE = 1.5;
	public static int scaledWidth = (int) (width * SCALE);
	public static int scaledHeight = (int) (height * SCALE);
	
	//
	private boolean isRunning = true;
	
	//Determine maximum sleep time between frames
	public final static int GOAL_FPS = 2;
	private final long RENDER_TIME = (long) 1.0 / GOAL_FPS * 1000;

	public Escala() {
		// JFrame
		frame = new JFrame();
		frame.setTitle("Escala Test");
		frame.setSize( scaledWidth, scaledHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		
		// Canvas
		canvas = new Canvas(config);
		canvas.setSize( scaledWidth, scaledHeight);
		frame.add(canvas, 0);
		
		// Background & buffer
		backgroundImage = config.createCompatibleImage(scaledWidth,  scaledHeight, Transparency.OPAQUE);
		canvas.createBufferStrategy(2);
		do {
			strategy = canvas.getBufferStrategy();
		} while (strategy == null);
		
		//start game engine
		run();
		
	}
	
	private Graphics2D getBuffer() {
		if (graphics == null) {
			try{
				graphics = (Graphics2D) strategy.getDrawGraphics();
			} catch (IllegalStateException e) {
				return null;
				//TODO:: more meaningful error message
			}
		}
		return graphics;
	}
	
	
	// ***EVERYONE NEEDS TO CARE ABOUT THIS***
	// THIS IS THE MAIN LOOP THAT STARTS THE ENGINE AND
	// CALLS UPDATE AND RENDER FUNCTIONS (also in Engine)
	public void run() {
		
		//TODO: replace with background image for game (MAP)
		backGraphics = (Graphics2D) backgroundImage.getGraphics();
		
		//TODO: Menu stuff goes here
		//Menu menu = new Menu();  
		
		//Setup Game Engine
		Engine gameEngine = new Engine();
				
		while(isRunning) {
			long startTime = System.nanoTime();
			
			//Update game state and logic
			gameEngine.updateGame();
			
			//Update and redraw graphics
			Graphics2D backbuffer = getBuffer();
			
			//Render foreground images
			gameEngine.renderGame(backGraphics);
				
			//Draw and dispose buffer
			backbuffer.drawImage(backgroundImage, 0, 0, null);
			backbuffer.dispose();
				
			//update screen
			graphics.dispose();
			graphics = null;
			try {
				strategy.show();
				Toolkit.getDefaultToolkit().sync();
			} catch (NullPointerException e) {
				break;
			} catch (IllegalStateException e) {
				break;
			}
			
			//Sleep until we get desired frame rate
			long totalTime = (System.nanoTime() - startTime) / 1000000;
			try {
				Thread.sleep(Math.max(0, RENDER_TIME - totalTime));
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
			
		}
		
		// TODO After Game clean up
		// TODO Save option???
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Escala game = new Escala();
	}

}
