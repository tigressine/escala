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
    GameState state;

	public PolyClick(GameState state)
	{
		poly = PolyMouseList.getInstance();
        this.state = state;
	}

    public void mouseClicked(MouseEvent e) {
       // eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);

        //Checks to see if in Region, then highlight region till clicked out of
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = state.getFrame().getLocation();

        Point containsPoint = new Point(p.x - r.x, p.y - r.y - 23);
        for (Region region : state.getAllRegions()) {
            if (region.contains(containsPoint, state.getScale())) {
                region.select();
            }
            else {
                region.deselect();
            }
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
