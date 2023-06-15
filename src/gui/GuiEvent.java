package gui;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.EventObject;

/**
 *
 * @author tadaki
 */
public class GuiEvent extends EventObject {

    //イベントの種類
    public static enum EventType {
        NewArea,//表示領域変更
        SetCenter;//中心変更
    }

    private final EventType eventType;
    private Point point=null;
    private Rectangle2D.Double rect=null;

    public GuiEvent(Object source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Rectangle2D.Double getRect() {
        return rect;
    }

    public void setRect(Rectangle2D.Double rect) {
        this.rect = rect;
    }

    public EventType getEventType() {
        return eventType;
    }

}
