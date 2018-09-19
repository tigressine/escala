// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.io.*;
import java.sql.*;
import escala.database.*;

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
    }
    
    public static void main(String[] args) {
        Escala escala = new Escala();
        //Portal portal = new Portal();
        //portal.addRegion("data/raw/regions/NorthAmerica.txt");
        //System.out.println(portal.getRegion("North America"));
    }
}
