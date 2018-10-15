// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
import escala.graphics.Map;
import escala.graphics.Sprite;
import java.lang.Math;

/*
 * This class does two things:
 *      updateGame() -> apply game logic
 *      renderGame() -> draw results
 *
 * */

public class Engine {
    public final int NUM_SPRITES = 4;
    private int frameCount = 0;

    Sprite[] sprites = null;
    private Game game;

    //initialize map, logic, random events, and other necessary classes
    Map map = null;
    Logic logic = null;
    // RandomEvent
    // other...

    //TODO::: setup map and sprites...
    public Engine(Game game){
        this.game = game;
        sprites = new Sprite[NUM_SPRITES];

        sprites[0] = new Sprite(0, 0, 300, this.game);
        sprites[0].setColor(Color.GREEN);

        sprites[1] = new Sprite(500, 0, 13, this.game);
        sprites[1].setColor(Color.BLUE);

        sprites[2] = new Sprite(0, 500, 18, this.game);
        sprites[2].setColor(Color.RED);

        sprites[3] = new Sprite(500, 500, 500, this.game);
        sprites[3].setColor(Color.WHITE);

        map = new Map(this.game);
        logic = game.getLogic();
    }

    public void updateGame() {
        //TODO::: respond to user input (if necessary)

        //TODO::: calculate and apply income and expenses
        if(frameCount >= (60 /Math.pow((double)game.getGameSpeed(),2.0)))
        {
            logic.timedUpdate();
            frameCount = 0;
        }
        else 
            frameCount++;
        

        //TODO::: generate random event

        //NOTE:  this is just temp stuff for sprite animations...
        // not very important...
        Random rand = new Random();

        for(int i = 0; i < NUM_SPRITES; i++){
            if(sprites[i].arrivedAtDestination()){
                Point p = new Point();
                double x = rand.nextInt(game.getWidth() - sprites[i].getWidth());
                double y = rand.nextInt(game.getHeight() - sprites[i].getHeight());
                p.setLocation(x, y);
                sprites[i].setDestination(p);
            }
            sprites[i].update();
        }
    }

    public void renderGame(Graphics2D g) {
        map.renderMap(g);

        for(int i = 0; i < NUM_SPRITES; i++){
            sprites[i].paintComponent(g);
        }

    }
}
