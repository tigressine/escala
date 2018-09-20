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
        //Escala escala = new Escala();
        try {
            GameState state = new GameState();
            System.out.println("All events");
            for (int i = 0; i < 10; i++) {
                System.out.println(state.getRandomEvent());
            }
            System.out.println("\nPositive events");
            for (int i = 0; i < 10; i++) {
                System.out.println(state.getRandomPositiveEvent());
            }
            System.out.println("\nNegative events");
            for (int i = 0; i < 10; i++) {
                System.out.println(state.getRandomNegativeEvent());
            }
            System.out.println("\nSpecific events");
            for (int i = 0; i < 10; i++) {
                System.out.println(state.getRandomEvent(.7, .8));
            }
            state.closePortal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
