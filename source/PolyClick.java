// Part of Escala.
// Written by Jonathan Ponader.

package escala;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import escala.graphics.PolyMouseList;
import escala.GameState;
import escala.graphics.Map;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.MouseInfo;


class PolyClick implements MouseListener{

    private static final int NUM_REGIONS = 10;

    PolyMouseList poly;
    GameState myGame;

	public PolyClick ()
	{
		poly = PolyMouseList.getInstance();
        myGame = GameState.getInstance();
	}

    public void mouseClicked(MouseEvent e) {
       // eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);

        //Checks to see if in Region, then highlight region till clicked out of
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = myGame.getFrame().getLocation();

        int region = poly.contains(new Point((p.x - r.x),(p.y - r.y - 23)), myGame.getScale());

        if(region > NUM_REGIONS)
        {
            Map.setSkip(Integer.MAX_VALUE);
            Map.setClick(false);
        }
        else
        {
            Map.setSkip(region);
            Map.setClick(true);
        }
    }

	private void eventOutput(String eventDescription, MouseEvent e) {
        /*
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + ".");
        */
    }
     
    public void mousePressed(MouseEvent e) {
       // eventOutput("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);
    }
     
    public void mouseReleased(MouseEvent e) {
       // eventOutput("Mouse released (# of clicks: " + e.getClickCount() + ")", e);
    }
     
    public void mouseEntered(MouseEvent e) {
        //eventOutput("Mouse entered", e);
    }
     
    public void mouseExited(MouseEvent e) {
       // eventOutput("Mouse exited", e);
    }
}
