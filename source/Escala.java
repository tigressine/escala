/*
 * This class initializes GameState (game variables) and the Database, 
 * 		and provides entry into the game
 */

public class Escala {
	
	public Escala() {
		
		// TODO: initialize database instance here... attach to myGame
		//
		
		Viewer gameViewer = new Viewer();
		
		gameViewer.run();
	}
	
	public static void main(String[] args) {
		Escala escala = new Escala();
	}
}
