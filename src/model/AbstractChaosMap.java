package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author tadaki
 */
public abstract class AbstractChaosMap {

    protected BufferedImage image;
    protected int maxIter = 512;//maximum iterations
    protected double radius = 2.;//
    protected Dimension dimension;
    protected Point2D.Double origin;
    protected double range = 4.;

    public AbstractChaosMap(Dimension dimension) {
        this.dimension = dimension;
        origin = new Point2D.Double(-2., -2.);
    }

    public void initImage() {
        int width = dimension.width;
        int height = dimension.height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (java.awt.Graphics2D) image.getGraphics();
        g.setBackground(new Color(0, 255, 255));
        g.fillRect(0, 0, width, height);
    }

    abstract protected Color onePoint(Point2D.Double c);

    protected Point2D.Double op(Point2D.Double p, Point2D.Double c) {
        double x = p.x * p.x - p.y * p.y + c.x;//real part
        double y = 2. * p.x * p.y + c.y;//imaginary part
        return new Point2D.Double(x, y);
    }

    public BufferedImage createImage() {
        int width = dimension.width;
        int height = dimension.height;
        Graphics g = image.getGraphics();
        for (int ix = 0; ix < width; ix++) {
            for (int iy = 0; iy < height; iy++) {
                double x = range * ix / width + origin.x;
                double y = range * iy / height + origin.y;
                Point2D.Double c = new Point2D.Double(x, y);
                Color color = onePoint(c);
                g.setColor(color);
                g.fillRect(ix, iy, 1, 1);
            }
        }
        return image;
    }

    public void resetArea() {
        origin.setLocation(-2., -2.);
        range = 4.;
    }

    public void selectArea(Rectangle2D.Double rect) {
        double x = rect.getX();
        double y = rect.getY();
        double w = rect.getWidth();
        double h = rect.getHeight();
        double m = Math.max(w, h);
        if (m < 5) {//Ignore too small regions
            return;
        }
        x = origin.x + (x / dimension.width) * range;
        y = origin.y + (y / dimension.height) * range;
        origin.setLocation(x, y);
        range = range * (m / dimension.width);
    }

    public void selectCenter(Point center) {
        double x = ((double) center.x / dimension.width) * range
                + origin.x - range / 2;
        double y = ((double) center.y / dimension.height) * range
                + origin.y - range / 2;
        origin.setLocation(x, y);
    }

    public void resetRadius() {
        radius = 2.;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
