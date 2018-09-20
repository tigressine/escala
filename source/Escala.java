// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.io.*;
import java.sql.*;

/*
 * This class initializes GameState (game variables) and the Database, 
 *      and provides entry into the game
 */

public class Escala {
    
    public Escala() {
        GameState state = null;
        try {
            state = new GameState();
        }
        catch (SQLException | IOException exception) {
            exception.printStackTrace();
            System.exit(-1);    
        }
        
        Menu menu = new Menu(state);
        Viewer gameViewer = new Viewer(state);
        gameViewer.run(); 
        state.closePortal();
    }
    
    public static void main(String[] args) {
        Escala escala = new Escala();
        /*
        try {
            Loader loader = new Loader();
            Portal portal = new Portal();
            loader.createRegions();

            loader.close();
            portal.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
