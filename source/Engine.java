// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
import escala.graphics.Map;
import escala.graphics.Sprite;

/*
 * This class does two things:
 *      updateGame() -> apply game logic
 *      renderGame() -> draw results
 * 
 * */

public class Engine {
    public final int NUM_SPRITES = 4;
    
    Sprite[] sprites = null;
    private GameState state;

    //initialize map, logic, random events, and other necessary classes
    Map map = null;
    // Logic
    // RandomEvent
    // other...
    
    //TODO::: setup map and sprites...
    public Engine(GameState state){
        this.state = state;
        sprites = new Sprite[NUM_SPRITES];
        
        sprites[0] = new Sprite(0, 0, 30, this.state);
        sprites[0].setColor(Color.GREEN);
    
        sprites[1] = new Sprite(500, 0, 13, this.state);
        sprites[1].setColor(Color.BLUE);
        
        sprites[2] = new Sprite(0, 500, 18, this.state);
        sprites[2].setColor(Color.RED);
        
        sprites[3] = new Sprite(500, 500, 15, this.state);
        sprites[3].setColor(Color.WHITE);
        
        map = new Map(this.state);
    }
    
    public void updateGame() {
        //TODO::: respond to user input (if necessary)
        
        //TODO::: calculate and apply income and expenses
        
        //TODO::: generate random event
        
        //NOTE:  this is just temp stuff for sprite animations...
        // not very important...
        Random rand = new Random();
        
        for(int i = 0; i < NUM_SPRITES; i++){
            if(sprites[i].arrivedAtDestination()){
                Point p = new Point();
                double x = rand.nextInt(state.getWidth() - sprites[i].getWidth());
                double y = rand.nextInt(state.getHeight() - sprites[i].getHeight());
                p.setLocation(x, y);
                sprites[i].setDestination(p);
            }
            sprites[i].update();
        }
    }

    public void renderGame(Graphics2D g) {
        //TODO:  render background image (MAP) here...
        map.renderMap(g);
        
        //TODO:  render sprites
        for(int i = 0; i < NUM_SPRITES; i++){
            sprites[i].paintComponent(g);
        }
        
        //TODO: render other stuff (charts, buttons)
    }
}
