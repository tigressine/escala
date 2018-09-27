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
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Escala");
            frame.setSize(200 ,200);
            frame.setVisible(true);
        }
        else if(stats.contains(p))
            System.out.println("Big STATS boy");

        else if(share.contains(p))
            System.out.println("I own that shit");

        else if(time.contains(p))
            System.out.println("Got time on my MIND");

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
