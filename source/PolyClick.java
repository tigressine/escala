// Part of Escala.
// Written by Jonathan Ponader and Barath Tirumala.

package escala;

import escala.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import escala.graphics.*;
import escala.structures.*;

class PolyClick implements MouseListener
{
    Game game;
    Logic logic;
    Rectangle cash  = new Rectangle(0,577,230,71);
    Rectangle stats = new Rectangle(260,600,632,48);
    Rectangle share = new Rectangle(922,577,230,71);
    Rectangle pause = new Rectangle(980,0,50,48);
    Rectangle play = new Rectangle(1020,0,50,48);
    Rectangle fast = new Rectangle(1080,0,72,48);

    Color background = Color.decode("#1981C9");
    Color buttonCol = Color.decode("#567A4C");

    public PolyClick(Game game)
    {
   	    this.game = game;
	    this.logic = game.getLogic();
    }

    // Takes Action on Mouse Click
    public void mouseClicked(MouseEvent e) {
        Point frameLoc = game.getFrame().getLocation();
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));

        if(cash.contains(mouse)){
            upgradePopup("Cash");
        }
        else if(stats.contains(mouse)){
            upgradePopup("Stats");
        }
        else if(play.contains(mouse)){
            game.continueGame();
        }
        else if(pause.contains(mouse)){
            game.pauseGame();
        }
        else if(fast.contains(mouse)){
            game.increaseSpeed();
        }
        else if(share.contains(mouse)){
            MSharePopup("Market");
        }
        else
        {
            for (Region region : game.getAllRegions()) {
                if (region.contains(mouse)) {
                    if (region.isSelected()) {
                        // Check to see if a Regions puchase bubble was click on
                        if(!region.isPurchased()){
                            Point p = region.getCenter();
                            Rectangle button = new Rectangle(p.x,p.y, 70, 40);

                            if(button.contains(mouse)){
                                if(logic.purchaseRegion(region)){
                                    region.purchase();
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "You do not have enough Resources to buy this Region");
                            }
                        }
                    }
                    else {
                        region.select();
                    }
                }
                else {
                    region.deselect();
                }
            }
        }
    }

    //Default popup for in-game settings
    public void simplePopup(String title){
        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setTitle(title);
        popup.setSize(400 ,300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);
        popup.setVisible(true);
    }

    //Popup when clicked on upgrading option
    public void upgradePopup(String title){
        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setTitle(title);
        popup.setSize(540, 360);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);

        JPanel ht = new JPanel();
        ht.setLayout(null);

        ht.setOpaque(true);
        ht.setBackground(background);

        JLabel setTitle = new JLabel("HOW-TO");
        setTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        setTitle.setBounds(160, 40, 240, 60);

        //format as html text for how-to instrutcions
        String HTML_CONTENT = "<html><body><ol><li>Press Play/Pause to stop or resume time</li><li>Press fast-forward to speed up game environment time</li><li>The first region chosen is free to expand into</li><li>After the first region, purchase others to expand</li><li>Enjoy and have fun!</li></body></html>";
        JLabel label = new JLabel (HTML_CONTENT);
        label.setBounds(100, 100, 360, 120);
        ht.add(label);

        ht.add(setTitle);
        popup.add(ht);

        popup.setVisible(true);

        // pause game
        game.pauseGame();

        // continue game when window closes.
        popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                popup.dispose();
                game.continueGame();
            }
        });
    }

    //Market share popup with pie chart
    public void MSharePopup(String title){
        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setTitle(title);
        popup.setSize((int) (game.getWidth() * .75), (int) (game.getHeight() * .75));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);
        popup.getContentPane().setBackground(background);

        Logic logic = game.getLogic();
        int markShare = (int)logic.getShare();
        System.out.println(markShare);

        //Declare pie chart
        PieChart pc = new PieChart(markShare);

        JButton next = new JButton("NEXT");
        next.setBounds(popup.getSize().width/2 - 70, 350, 120, 30);

        JLabel mstitle = new JLabel("Share Graph");
        mstitle.setFont(new Font("Serif", Font.PLAIN, 50));
        mstitle.setBounds(popup.getSize().width/2 - 140, 40, 300, 60);
        popup.getContentPane().add(mstitle);
        //popup.getContentPane().add(next);

        popup.getContentPane().add(pc);

        popup.setVisible(true);

        // pause game
        game.pauseGame();

        // continue game when window closes.
        popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                popup.dispose();
                game.continueGame();
            }
        });
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }
}
