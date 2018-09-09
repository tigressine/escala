

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 
 * TODO::: Ideally, we will pull NUM_REGIONS and regionNames from database
 * 				regionNames can be stored as paths...
 * 
 * 		Use cursor location to determine which region to highlight
 * 
 * */

public class Map {
	
	private static final int NUM_REGIONS = 10;
	BufferedImage background = null;
	BufferedImage[] regions = new BufferedImage[NUM_REGIONS];
	String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
			"NorthAfrica", "NorthAmerica", "Ocenia", "SouthAfrica", "SouthAmerica", "WesternEurope"};
	BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
	
	int imageWidth = 1152;
	int imageHeight = 648;
	
	
	public Map(){
		
		// try to load every image
		try { 
			background = ImageIO.read(new File("assets/Background.png"));
			
			for(int i = 0; i < NUM_REGIONS; i++)
				regions[i] = ImageIO.read(new File("assets/" + regionNames[i] + ".png"));
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}

	public void renderMap(Graphics2D g) {
		GameState myGame = GameState.getInstance();
		
		// render background
		if(background != null)
			//g.drawImage(Image, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, observer)
			g.drawImage(background, 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
		else{
			g.setBackground(Color.BLACK);
			g.clearRect(0, 0, myGame.getWidth(), myGame.getHeight());
		}
		
		// render each region
		for(int i = 0; i < NUM_REGIONS; i++)
			g.drawImage(regions[i], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
		
		// TODO use cursor location to determine which region should be highlighted
		
		// TODO render highlighted region
		
	}

}
