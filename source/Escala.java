package escala;

/*
 * This class initializes GameState (game variables) and the Database, 
 * 		and provides entry into the game
 */

public class Escala {
	
	public Escala() {
		
		GameState myGame = GameState.getInstance();
		
		// TODO: initialize database instance here... attach to myGame
		// myGame.setDatabaseInstance(dbInstance);   NOTE: this function does not exist yet.
		
		// Menu stuff
		Menu menu = new Menu();
		
		Viewer gameViewer = new Viewer();
		gameViewer.run(); 
	}
	
	public static void main(String[] args) {
		Escala escala = new Escala();
	}
}
