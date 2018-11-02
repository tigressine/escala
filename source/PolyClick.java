// Part of Escala.
// Written by Jonathan Ponader.

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

	public PolyClick(Game game)
	{
        this.game = game;
        this.logic = game.getLogic();
    }

    public void mouseClicked(MouseEvent e) {
        Point frameLoc = game.getFrame().getLocation();
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Insets margin = game.getFrame().getInsets();
        double scale = game.getScale();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));


        System.out.println(mouse.x + " " + mouse.y);

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

    public void simplePopup(String title){
      JFrame popup = new JFrame();
      popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      popup.setTitle(title);
      popup.setSize(400 ,300);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);
      popup.setVisible(true);
    }

    public void upgradePopup(String title){
        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setTitle(title);
        popup.setSize((int) (game.getWidth() * .75), (int) (game.getHeight() * .75));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);


        popup.setVisible(true);

        //canvas for upgrades

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

    public void MSharePopup(String title){
        JFrame popup = new JFrame();
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setTitle(title);
        popup.setSize((int) (game.getWidth() * .75), (int) (game.getHeight() * .75));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);

        Logic logic = game.getLogic();
        int markShare = (int)logic.getShare();
        System.out.println(markShare);


        PieChart pc = new PieChart(markShare);
        //pc.paint(g,markShare);

        JButton next = new JButton("NEXT");
        next.setBounds(popup.getSize().width/2 - 70, 350, 120, 30);

        JLabel mstitle = new JLabel("Share Graph");
        mstitle.setFont(new Font("Serif", Font.PLAIN, 50));
        mstitle.setBounds(popup.getSize().width/2 - 140, 40, 300, 60);
        popup.getContentPane().add(mstitle);
        popup.getContentPane().add(next);

        popup.getContentPane().add(pc);

        popup.setVisible(true);

        //canvas for upgrades

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
