// Part of Escala.
// Written by Barath Tirumala and Jonathan Ponader.


package escala.graphics;

import java.awt.*;
import javax.swing.*;

public class PieChart extends JComponent {
    int val;
    public PieChart(int x) {
        this.val = x;
    }

    public PieChart() {

    }

    public void paint(Graphics g) {
        Slice[] slices = {
            new Slice(val, Color.YELLOW), new Slice(100-val, Color.BLACK)
            };

        Rectangle bounds = getBounds();
        Dimension dm = bounds.getSize();
        Dimension ndm = new Dimension(dm.width /2, dm.height /2);
        Rectangle nbound = new Rectangle(ndm);
        drawPie((Graphics2D) g, nbound, slices);
    }

    public void paint (Graphics g, Point center, int share){
        Slice[] slices = {new Slice(share, Color.YELLOW), new Slice(100-share, Color.BLACK)};
        Rectangle bounds = new Rectangle(40,40);

        drawPie((Graphics2D)g, center, bounds, slices);
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
            g.fillArc(area.x + 210, area.y + 150, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }

    void drawPie(Graphics2D g, Point center, Rectangle bound, Slice[] slices) {
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
            g.fillArc(center.x, center.y, bound.width, bound.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
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
