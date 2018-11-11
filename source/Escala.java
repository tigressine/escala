// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.io.*;
import java.sql.*;
import escala.structures.*;

/*
 * This class initializes Game (game variables) and the Database, 
 *      and provides entry into the game
 */

public class Escala {
    
    public Escala() {
        Game game = null;
        try {
            game = new Game();
        }
        catch (SQLException | IOException exception) {
            exception.printStackTrace();
            System.exit(-1);    
        }
        
        Menu menu = new Menu(game);
        Viewer gameViewer = new Viewer(game);
        gameViewer.run(); 
        game.closePortal();
    }
    
    public static void main(String[] args) {
        Escala escala = new Escala();
    }
}
