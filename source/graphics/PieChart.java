// Part of Escala.
// Written by Barath Tirumala.


package escala.graphics;

import java.awt.*;
import javax.swing.*;

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

class Slice {
    double value;
    Color color;
    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}