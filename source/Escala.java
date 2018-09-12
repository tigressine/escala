package escala;

import escala.database.*;
import java.sql.*;
import java.io.*;

/*
 * This class initializes GameState (game variables) and the Database, 
 *      and provides entry into the game
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
    
    public static void main(String[] args) throws SQLException, IOException {
        Escala escala = new Escala();
        Portal portal = new Portal();
        portal.addRegion("data/raw/regions/NorthAmerica.txt");
        System.out.println(portal.getRegion("North America"));
    }
}
