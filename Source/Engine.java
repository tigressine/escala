import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

/*
 * This class contains:
 * 		updateGame() -> apply game logic
 * 		renderGame() -> render results
 * 
 * */

public class Engine {
	public final int NUM_SPRITES = 4;
	
	Sprite[] sprites = new Sprite[NUM_SPRITES];
	
	//TODO::: setup map and sprites...
	public Engine(){
		sprites[0] = new Sprite(0, 0, 10);
		sprites[0].setColor(Color.GREEN);
	
		sprites[1] = new Sprite(500, 0, 7);
		sprites[1].setColor(Color.BLUE);
		
		sprites[2] = new Sprite(0, 500, 3);
		sprites[2].setColor(Color.RED);
		
		sprites[3] = new Sprite(500, 500, 5);
		sprites[3].setColor(Color.WHITE);
	}
	
	public void updateGame() {
		
		//NOTE:  this is just temp stuff for sprite animations...
		// not very important...
		Random rand = new Random();
		
		for(int i = 0; i < NUM_SPRITES; i++){
			if(sprites[i].arrivedAtDestination()){
				Point p = new Point();
				double x = rand.nextInt(Escala.scaledWidth);
				double y = rand.nextInt(Escala.scaledHeight);
				p.setLocation(x, y);
				sprites[i].setDestination(p);
			}
			sprites[i].update();
		}
	}

	public void renderGame(Graphics2D g) {
		//TODO:  render background image (MAP) here...
		g.clearRect(0, 0, Escala.scaledWidth, Escala.scaledHeight);
		
		for(int i = 0; i < NUM_SPRITES; i++){
			sprites[i].paintComponent(g);
		}
	}

}
