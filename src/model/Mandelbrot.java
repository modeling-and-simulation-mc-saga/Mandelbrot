package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

/**
 *
 * @author tadaki
 */
public class Mandelbrot extends AbstractChaosMap{

    public Mandelbrot(Dimension dimension) {
        super(dimension); 
    }

    @Override
    protected Color onePoint(Point2D.Double c) {
        Point2D.Double p = new Point2D.Double(c.x,c.y);
        for (int i = 1; i <= maxIter; i++) {
            Point2D.Double r = op(p, c);
            if (r.x * r.x + r.y * r.y > radius * radius) {//out of bound
                int s = (int) ((1. - (double) i / maxIter) * 256.);
                return new Color(s, s, 255 - s);
            }
            p.setLocation(r.x, r.y);
        }
        return new Color(0, 0, 0);
    }


}
