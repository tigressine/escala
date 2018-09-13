package escala;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import escala.PolyClick;

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
    
    //For performance testing
    private long frameCount = 0;
    
    // CONSTRUCTOR
    public Viewer(){
        GameState myGame = GameState.getInstance();
        frame = myGame.getFrame();
    }
    
    public void gameViewer(){
        GameState myGame = GameState.getInstance();
        frame = new JFrame();
        myGame.setFrame(frame);
        frame.setTitle("Escala Test");
        frame.setSize( myGame.getWidth(), myGame.getHeight() + myGame.getFrameHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);
                
        // Canvas
        canvas = new Canvas(configuration);
        canvas.setSize( myGame.getWidth(), myGame.getHeight());
        frame.add(canvas, 0);

        canvas.addMouseListener(new PolyClick());
            
        // Establish Buffer Strategy
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
    }
    
    public void menuViewer(){
        
    }
    
    
    // Sleep the required number of milliseconds to achieve desired frame rate
    // We don't want 6000+ fps
    private void sleepForFrameRate(long startTime, long allowedTime) {
        long elapsedTime = (System.nanoTime() - startTime) / 1000000;
        
        //every 100 frames, display percent of frame time actually utilized.
        if(frameCount++ % 100 == 0)
            System.out.println( (double) allowedTime / (double) elapsedTime);
        
        try {
            Thread.sleep(Math.max(0, allowedTime - elapsedTime));
        } catch (InterruptedException e) {
            Thread.interrupted();
            e.printStackTrace();
        }
    }
    
    // ***EVERYONE NEEDS TO CARE ABOUT THIS***
    // THIS IS THE MAIN LOOP THAT STARTS THE ENGINE AND
    // CALLS UPDATE AND RENDER FUNCTIONS (also in Engine)
    public void run(){
        
        GameState myGame = GameState.getInstance();
        
        while(myGame.gameIsRunning() != true) {
            // stall here while menu is doing its thing...
            // when user selects difficulty, gameIsRunning will be set to true
            // NOTE: sleeping here does not affect menu listeners
            
            //sleep for .5 seconds between checks (don't waste CPU cycles)
            sleepForFrameRate(System.nanoTime(), 500);
        }
        
        
        //setup game window environment
        gameViewer();
        
        // Setup Game Engine
        Engine engine = new Engine();
        
        
        // MAIN GAME LOOP
        while( myGame.gameIsRunning() ) {
            
            //get current time for frame rate calculations
            long startTime = System.nanoTime();
            
            graphicsBuffer = (Graphics2D) strategy.getDrawGraphics();
        
            if ( strategy.contentsLost() != true ){
                
                // TODO: check for user input here
                
                engine.updateGame();
                
                //render map, sprites, and other stuff here
                engine.renderGame(graphicsBuffer);
                
                strategy.show();
                graphicsBuffer.dispose();
            } else {
                System.err.println("ERROR: Lost graphics context for game");
                myGame.stopGame();
            }
            
            sleepForFrameRate(startTime, myGame.getFrameTime());
        }
        
        // TODO After Game clean up
        // TODO Save option???
        
    }
}
