// Part of Escala.
// Written by Jonathan Ponader.

package escala;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import escala.Game;
import escala.graphics.Map;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.*;
import escala.structures.*;

class PolyClick implements MouseListener{

    private static final int NUM_REGIONS = 10;

    Game game;
    Rectangle cash  = new Rectangle(0,577,230,71);
    Rectangle stats = new Rectangle(260,600,632,48);
    Rectangle share = new Rectangle(922,577,230,71);
    Rectangle pause = new Rectangle(980,0,50,48);
    Rectangle play = new Rectangle(1020,0,50,48);
    Rectangle fast = new Rectangle(1080,0,72,48);

	public PolyClick(Game game)
	{
        this.game = game;
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
                        region.deselect();
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

    public void tempPopup(String title){
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

        Logic logic = new Logic();
        int markShare = logic.marketShare;
        System.out.println(markShare);
        PieChart pc = new PieChart(markShare);

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

class Slice {
    double value;
    Color color;
    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}
class PieChart extends JComponent {
    int val;
    PieChart(int x) {
        this.val = x;
    }
    public void paint(Graphics g) {
     Slice[] slices = {
        new Slice(val, Color.white), new Slice(100-val, Color.black)
     };
        Rectangle bounds = getBounds();
        Dimension dm = bounds.getSize();
        Dimension ndm = new Dimension(dm.width /2, dm.height /2);
        Rectangle nbound = new Rectangle(ndm);
        drawPie((Graphics2D) g, nbound, slices);
    }
    void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0.0D;

        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);
            g.setColor(slices[i].color);
            g.fillArc(area.x + 210, area.y +100, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }
}
