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

    GameState state;
    Rectangle cash  = new Rectangle(0,577,230,71);
    Rectangle stats = new Rectangle(260,600,632,48);
    Rectangle share = new Rectangle(922,577,230,71);
    Rectangle pause = new Rectangle(980,0,50,48);
    Rectangle play = new Rectangle(1020,0,50,48);
    Rectangle fast = new Rectangle(1080,0,72,48);

	public PolyClick(GameState state)
	{
        this.state = state;
	}

    public void mouseClicked(MouseEvent e) {
        Point frameLoc = state.getFrame().getLocation();
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Insets margin = state.getFrame().getInsets();
        double scale = state.getScale();

        mouse.x = (int)((1/scale) * (double)(mouse.x - frameLoc.x - margin.left));
        mouse.y = (int)((1/scale) * (double)(mouse.y - frameLoc.y - margin.top));


        System.out.println(mouse.x + " " + mouse.y);

        if(cash.contains(mouse)){
            tempPopup("Cash");
        }
        else if(stats.contains(mouse)){
            tempPopup("Stats");
        }

        else if(play.contains(mouse)){
            state.startGame();
            System.out.println("play");
        }

        else if(pause.contains(mouse)){
            state.stopGame();
            System.out.println("pause");
        }

        else if(fast.contains(mouse)){
            state.startGame();
            System.out.println("fast");
        }

        else if(share.contains(mouse)){
            tempPopup("Market");
        }

        else
        {
            for (Region region : state.getAllRegions()) {
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

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
