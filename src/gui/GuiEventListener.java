package gui;

import java.util.EventListener;

/**
 *
 * @author tadaki
 */
public interface GuiEventListener extends EventListener{
    public void guiChanged(GuiEvent evt);
}
