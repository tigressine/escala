// Part of Escala.
// Written by Spencer Phillips.

package escala;

import java.io.*;
import java.sql.*;

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
        //Escala escala = new Escala();
        /*        
        try {
            Game game = new Game();
            System.out.println("All events");
            for (int i = 0; i < 10; i++) {
                System.out.println(game.getRandomEvent());
            }
            System.out.println("\nPositive events");
            for (int i = 0; i < 10; i++) {
                System.out.println(game.getRandomPositiveEvent());
            }
            System.out.println("\nNegative events");
            for (int i = 0; i < 10; i++) {
                System.out.println(game.getRandomNegativeEvent());
            }
            System.out.println("\nSpecific events");
            for (int i = 0; i < 10; i++) {
                System.out.println(game.getRandomEvent(.7, .8));
            }
            game.closePortal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            Portal portal = new Portal();
            SkillTree tree = portal.getSkillTree("Sample");
            System.out.print(tree);

            SkillTree tree2 = portal.getSkillTree("Sample2");
            System.out.print(tree2);

            portal.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
