import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/*
 * This class sets up the JFrame and Canvas and contains the main game loop. 
 * It also enables double buffering, and enforces fps limits.
 * */

public class Viewer{
	private JFrame frame;
	private Canvas canvas;
	
	//Double Buffer Graphics Configuration
	private GraphicsConfiguration configuration = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	private BufferStrategy strategy;
	private Graphics2D graphicsBuffer;
	
	
	
	public Viewer(){
		// JFrame
		frame = new JFrame();
		frame.setTitle("Escala Test");
		frame.setSize( Escala.scaledWidth, Escala.scaledHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
				
		// Canvas
		canvas = new Canvas(configuration);
		canvas.setSize( Escala.scaledWidth, Escala.scaledHeight);
		frame.add(canvas, 0);
			
		// Establish Buffer Strategy
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
			
		// Start game
		run();
	}
	
	// Sleep the required number of millisecconds to achieve desired frame rate
	// We don't want 6000+ fps
	public void sleepForFrameRate(long startTime) {
		long totalTime = (System.nanoTime() - startTime) / 1000000;
		try {
			Thread.sleep(Math.max(0, Escala.FRAME_TIME - totalTime));
		} catch (InterruptedException e) {
			Thread.interrupted();
			e.printStackTrace();
			Escala.setGameIsRunning(false);
		}
	}
	
	// ***EVERYONE NEEDS TO CARE ABOUT THIS***
	// THIS IS THE MAIN LOOP THAT STARTS THE ENGINE AND
	// CALLS UPDATE AND RENDER FUNCTIONS (also in Engine)
	public void run(){
		
		//setup game environment
		// TODO: Menu stuff goes here
		// Menu menu = new Menu();
		
		// Setup Game Engine
		Engine engine = new Engine();
		
		// MAIN GAME LOOP
		while(Escala.gameIsRunning()){
			
			//get current time for frame rate calculations
			long startTime = System.nanoTime();
			
			graphicsBuffer = (Graphics2D) strategy.getDrawGraphics();
		
			if (!strategy.contentsLost()){
				
				engine.updateGame();
				
				// TODO: render map (and other background stuff) here
				
				engine.renderGame(graphicsBuffer);
				
				strategy.show();
				graphicsBuffer.dispose();
			} else {
				System.err.println("ERROR: Lost graphics context for game");
				Escala.setGameIsRunning(false);
			}
			
			sleepForFrameRate(startTime);
		}
		
		// TODO After Game clean up
		// TODO Save option???
		
	}
}
