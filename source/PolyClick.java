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

class PolyClick implements MouseListener {
    private GameState state;

	public PolyClick(GameState state) {
        this.state = state;
	}

    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = state.getFrame().getLocation();

        Point containsPoint = new Point(p.x - r.x, p.y - r.y - 23);
        for (Region region : state.getAllRegions()) {
            if (region.contains(containsPoint, state.getScale())) {
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
