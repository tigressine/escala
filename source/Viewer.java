// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import escala.PolyClick;
import java.awt.*;
import javax.swing.*;

// This should only be temporary stuff...
import java.awt.event.*;


/*
 * This class sets up the JFrame and Canvas and contains the main game loop.
 * It also enables double buffering, and enforces fps limits.
 * */

public class Viewer implements KeyListener{

    private GameState state;
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
    public Viewer(GameState state){
        this.state = state;
        frame = state.getFrame();
    }

    public void gameViewer(){
        frame = new JFrame();
        state.setFrame(frame);
        frame.setTitle("Escala Test");
        frame.setSize( state.getWidth(), state.getHeight() + state.getFrameHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIgnoreRepaint(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);

        // Canvas
        canvas = new Canvas(configuration);
        canvas.setSize( state.getWidth(), state.getHeight());
        frame.add(canvas, 0);

        //Adds Mouse Listener from class PolyClick
        canvas.addMouseListener(new PolyClick(state));

		//Adds Key Listener
		canvas.addKeyListener(this);

        // Establish Buffer Strategy
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
    }


	@Override
	public void keyPressed(KeyEvent e) {
		// get key that was pressed
		int keyCode = e.getKeyCode();
		//System.out.println("You Pressed A Key: " + keyCode);
		//System.out.println("Expected: " + KeyEvent.VK_P);

		// if key is p, pause / unpause game
		if(keyCode == KeyEvent.VK_P){
			if(state.gameIsPaused()){
				state.continueGame();
			} else {
				state.pauseGame();
			}
		}

		// TODO other key stuff ???
		// +/- to speed up or slow down game speed...
		// q to quit
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// leave this alone
	}

	@Override
    public void keyTyped(KeyEvent e) {
		// leave this alone
	}


    // Sleep the required number of milliseconds to achieve desired frame rate
    // We don't want 6000+ fps
    private void sleepForFrameRate(long startTime, long allowedTime) {
        long elapsedTime = (System.nanoTime() - startTime) / 1000000;

        //every 100 frames, display percent of frame time actually utilized.
        if(frameCount++ % 100 == 0) {
            //System.out.println( (double) allowedTime / (double) elapsedTime);
        }

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

        while(state.gameIsRunning() != true) {
            // stall here while menu is doing its thing...
            // when user selects difficulty, gameIsRunning will be set to true
            // NOTE: sleeping here does not affect menu listeners

            //sleep for .5 seconds between checks (don't waste CPU cycles)
            sleepForFrameRate(System.nanoTime(), 500);
        }


        //setup game window environment
        gameViewer();

        // Setup Game Engine
        Engine engine = new Engine(state);


        // MAIN GAME LOOP
        while( state.gameIsRunning() ) {

            //get current time for frame rate calculations
            long startTime = System.nanoTime();

            graphicsBuffer = (Graphics2D) strategy.getDrawGraphics();

            if ( strategy.contentsLost() != true ){

                // TODO: check for user input here

				// update game if game is not paused.
                if( state.gameIsPaused() != true ) {
                	engine.updateGame();
				}

                //render map, sprites, and other stuff here
                engine.renderGame(graphicsBuffer);

                strategy.show();
                graphicsBuffer.dispose();
            } else {
                System.err.println("ERROR: Lost graphics context for game");
                state.stopGame();
            }

            sleepForFrameRate(startTime, state.getFrameTime());
        }

        // TODO After Game clean up
        // TODO Save option???

    }
}
