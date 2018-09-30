// Part of Escala.
// Written by Jonathan Ponader.

package escala;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import escala.GameState;
import escala.graphics.Map;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.*;

class PolyClick implements MouseListener{

    private static final int NUM_REGIONS = 10;

    //PolyMouseList poly;
    GameState state;
    Rectangle cash  = new Rectangle(0,577,230,71);
    Rectangle stats = new Rectangle(260,600,632,48);
    Rectangle share = new Rectangle(922,577,230,71);
    Rectangle time = new Rectangle(955,0,197,48);

	public PolyClick(GameState state)
	{
		//poly = PolyMouseList.getInstance();
        this.state = state;
	}

    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = state.getFrame().getLocation();
        Insets margin = state.getFrame().getInsets();
        double scale = state.getScale();

        p = new Point((p.x - r.x - margin.left),(p.y - r.y - margin.top));

        p.x = (int)((1/scale) * (double)p.x);
        p.y = (int)((1/scale) * (double)p.y);

        System.out.println(p.x + " " + p.y);

        if(cash.contains(p)){
            System.out.println("Cash Money");
            //tempPopup("Cash Money");
			upgradePopup("Cash Money");
        }
        else if(stats.contains(p)){
            System.out.println("Big STATS boy");
            tempPopup("BIG boi");
        }
        else if(share.contains(p)){
            System.out.println("I own that shit");
            tempPopup("****");
        }
        else if(time.contains(p)){
            System.out.println("Got time on my MIND");
            tempPopup("TIMEONMYMIND");
        }
        else
        {
            for (Region region : state.getAllRegions()) {
                if (region.contains(p)) {
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
        popup.setSize((int) (state.getWidth() * .75), (int) (state.getHeight() * .75));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		popup.setLocation(dim.width/2-popup.getSize().width/2, dim.height/2-popup.getSize().height/2);
		popup.setVisible(true);

		//canvas for upgrades


		// pause game
		state.pauseGame();

		// continue game when window closes.
		popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    popup.dispose();
			        state.continueGame();
            }
        });
	}

	private void eventOutput(String eventDescription, MouseEvent e) {
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
