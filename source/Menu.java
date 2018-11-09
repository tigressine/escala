// Part of Escala.
// Written by Barath Tirumala.

package escala;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class Menu{

    //declare some variables
    private Game game;
    private JFrame frame;
    private static int width = 360;
    private static int height = 240;
    private final static double SCALE = 1.5;
    public static int scaledWidth = (int) (width * SCALE);
    public static int scaledHeight = (int) (height * SCALE);
    public static int gameWidth = 1600;
    public static int gameHeight = 900;
    JButton play = new JButton("PLAY");
    JButton set = new JButton("SETTINGS");
    JButton cred = new JButton("CREDITS");
    JButton back = new JButton("BACK");
    JButton backht = new JButton("BACK");
    JButton back2 = new JButton("BACK");
    JButton easy = new JButton("EASY");
    JButton backfromDiff = new JButton("BACK");
    JButton medium = new JButton("MEDIUM");
    JButton hard = new JButton("HARD");
    JButton exit = new JButton("EXIT");
    JButton loadFile = new JButton("How-To");
    JTextField prodName;

    // Product Buttons
    JButton p1 = new JButton("CAR");
    JButton p2 = new JButton("LAPTOP");
    JButton p3 = new JButton("BIKE");
    JButton p4 = new JButton("BLENDER");
    JButton p5 = new JButton("TOILET");
    JButton p6 = new JButton("TOASTER");

    //Over all card layout
    JPanel cards = new JPanel(new CardLayout());

    //Background color definitions
    Color background = Color.decode("#1981C9");
    Color buttonCol = Color.decode("#567A4C");

    public Menu(Game game) {
        //init frame
        this.game = game;
        game.setFrame(new JFrame());
        frame = game.getFrame();

        //Set key properties
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Escala");
        frame.setSize( scaledWidth, scaledHeight);

        //center frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);

        //add various pages into the "cardstack" to then switch between the screens
        addIndexCard(cards);
        addDiffCard(cards);
        addSetCard(cards);
        addCredCard(cards);
        addGameCard(cards);
        addHowTo(cards);
        addProdCard(cards);

        frame.add(cards);

        //tie buttons to their respective functions
        play.addActionListener(e -> toProd(frame));
        back.addActionListener(e -> toMenu(frame));
        back2.addActionListener(e -> toMenu(frame));
        backht.addActionListener(e -> toMenu(frame));
        backfromDiff.addActionListener(e -> toMenu(frame));
        set.addActionListener(e -> toSet(frame));
        cred.addActionListener(e -> toCred(frame));
        exit.addActionListener(e -> quit(frame));
        easy.addActionListener(e -> startGameEasy(frame));
        medium.addActionListener(e -> startGameMedium(frame));
        hard.addActionListener(e -> startGameHard(frame));
        loadFile.addActionListener(e -> startHowTo(frame));

        //product buttons set to certain vars
        p1.addActionListener(e -> toDiff(frame));
        p2.addActionListener(e -> toDiff(frame));
        p3.addActionListener(e -> toDiff(frame));
        p4.addActionListener(e -> toDiff(frame));
        p5.addActionListener(e -> toDiff(frame));
        p6.addActionListener(e -> toDiff(frame));

        //show screen
        frame.setVisible(true);
    }

    //add start menu
    public void addIndexCard(Container pane){
        JPanel index = new JPanel();
        index.setLayout(null);

        index.setOpaque(true);
        index.setBackground(background);

        JLabel title = new JLabel("ESCALA");
        title.setFont(new Font("Serif", Font.PLAIN, 50));
        title.setBounds(170, 40, 220, 60);
        play.setBounds(200, 120, 120, 30);
        set.setBounds(200, 160, 120, 30);
        cred.setBounds(200, 200, 120, 30);
        exit.setBounds(200, 240, 120, 30);


        index.add(title);
        index.add(play);
        index.add(set);
        index.add(cred);
        index.add(exit);

        play.setBackground(buttonCol);
        set.setBackground(buttonCol);
        cred.setBackground(buttonCol);
        exit.setBackground(buttonCol);

        pane.add(index, "INDEX");

        //Add background and color scheme

    }

    //add difficulty menu
    public void addDiffCard(Container pane){
        JPanel diff = new JPanel();
        diff.setLayout(null);

        diff.setOpaque(true);
        diff.setBackground(background);


        JLabel diffTitle = new JLabel("DIFFICULTY");
        diffTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        diffTitle.setBounds(120, 40, 300, 60);

        easy.setBounds(100, 120, 100, 100);
        medium.setBounds(210, 120, 100, 100);
        hard.setBounds(320, 120, 100, 100);
        backfromDiff.setBounds(200, 240, 120, 30);

        easy.setBackground(buttonCol);
        medium.setBackground(buttonCol);
        hard.setBackground(buttonCol);
        backfromDiff.setBackground(buttonCol);

        diff.add(diffTitle);
        diff.add(easy);
        diff.add(medium);
        diff.add(hard);
        diff.add(backfromDiff);

        pane.add(diff, "DIFF");

        // color schemes
    }

    //Product creation page
    //Has given choices to select from
    public void addProdCard(Container pane){
        JPanel prod = new JPanel();
        prod.setLayout(null);

        prod.setOpaque(true);
        prod.setBackground(background);

        prodName = new JTextField("enter name and choose", 16);
        prodName.setBounds(190,110,160,25);

        //Clears text field when clicked on
        prodName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                prodName.setText("");
            }
        });

        JLabel prodTitle = new JLabel("PRODUCTS");
        prodTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        prodTitle.setBounds(140, 40, 300, 60);

        p1.setBounds(110, 150, 100, 50);
        p2.setBounds(220, 150, 100, 50);
        p3.setBounds(330, 150, 100, 50);
        p4.setBounds(110, 210, 100, 50);
        p5.setBounds(220, 210, 100, 50);
        p6.setBounds(330, 210, 100, 50);

        p1.setBackground(buttonCol);
        p2.setBackground(buttonCol);
        p3.setBackground(buttonCol);
        p4.setBackground(buttonCol);
        p5.setBackground(buttonCol);
        p6.setBackground(buttonCol);

        prod.add(prodTitle);
        prod.add(p1);
        prod.add(p2);
        prod.add(p3);
        prod.add(p4);
        prod.add(p5);
        prod.add(p6);
        prod.add(prodName);

        pane.add(prod, "PROD");
    }

    //add settings menu
    public void addSetCard(Container pane){
        JPanel set = new JPanel();
        set.setLayout(null);

        set.setOpaque(true);
        set.setBackground(background);

        JLabel setTitle = new JLabel("SETTINGS");
        setTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        setTitle.setBounds(140, 40, 240, 60);


        set.add(setTitle);
        back.setBounds(200, 240, 120, 30);
        back.setBackground(buttonCol);
        set.add(back);


        loadFile.setBounds(200, 190, 120, 30);
        loadFile.setBackground(buttonCol);
        set.add(loadFile);

        JLabel frameScale = new JLabel("50");
        frameScale.setFont(new Font("Serif", Font.PLAIN, 20));
        frameScale.setBounds(250, 90, 240, 60);
        frameScale.setBackground(buttonCol);
        set.add(frameScale);

        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL);
        framesPerSecond.setBounds(160,140,200,30);
        framesPerSecond.setBackground(buttonCol);
        framesPerSecond.addChangeListener((ChangeEvent e) -> {
            frameScale.setText(String.valueOf(framesPerSecond.getValue()));
        });
        set.add(framesPerSecond);


        pane.add(set, "SET");
        //add setting options
    }

    //Adding tutorial selection in menu
    public void addHowTo(Container pane){
        JPanel ht = new JPanel();
        ht.setLayout(null);

        ht.setOpaque(true);
        ht.setBackground(background);

        JLabel setTitle = new JLabel("HOW-TO");
        setTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        setTitle.setBounds(160, 40, 240, 60);

        //format as html text
        String HTML_CONTENT = "<html><body><ol><li>Press Play to begin</li><li>Choose Difficulty level</li><li>Expand your product</li><li>Enjoy!</li></body></html>";
        JLabel label = new JLabel (HTML_CONTENT);
        label.setBounds(140, 120, 240, 60);
        ht.add(label);


        ht.add(setTitle);
        backht.setBounds(200, 240, 120, 30);
        backht.setBackground(buttonCol);
        ht.add(backht);

        pane.add(ht, "HOWTO");
    }

    //add credits menu
    public void addCredCard(Container pane){
        JPanel cred = new JPanel();
        cred.setLayout(null);

        cred.setOpaque(true);
        cred.setBackground(background);

        JLabel credTitle = new JLabel("CREDITS");
        credTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        credTitle.setBounds(155, 40, 220, 60);

        JLabel credText = new JLabel("#TeamEscala");
        credText.setFont(new Font("Serif", Font.PLAIN, 25));
        credText.setBounds(190, 40, 220, 150);

        cred.add(credTitle);
        cred.add(credText);
        back2.setBounds(200, 240, 120, 30);
        cred.add(back2);

        cred.setBackground(background);
        back2.setBackground(buttonCol);

        pane.add(cred, "CRED");

        //have credits page more text
    }

    public void addGameCard(Container pane){
        JPanel game = new JPanel();
        game.setLayout(null);

        pane.add(game, "GAME");
    }

    //switches to diff page
    private void toDiff(Container parent) {
        // PASS THIS VALUE TO GAME
        String productName = prodName.getText();

        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "DIFF");
    }

    //goes back to menu
    private void toMenu(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "INDEX");
    }

    //goes to settings
    private void toSet(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "SET");
    }

    //goes to credits
    private void toCred(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "CRED");
    }

    private void toProd(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "PROD");
    }

    private void startHowTo(Container parent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "HOWTO");
    }

    //exits game
    private void quit(Container parent) {
        System.exit(0);
    }

    private void startGameEasy(Container parent) {
        game.setDifficulty(Game.Difficulty.EASY);
        game.startGame();

        frame.dispose();
        game.setFrame(null);
    }

    private void startGameMedium(Container parent) {
        JOptionPane.showMessageDialog(null, "This page is currently under construction!");
    }

    private void startGameHard(Container parent) {
        JOptionPane.showMessageDialog(null, "This page is currently under construction!");
    }
}
