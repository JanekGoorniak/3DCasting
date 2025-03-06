package org.example;

import java.awt.*;

public interface Drawable
{
    void draw(Graphics g);
    default void addToDrawables(Drawable drawable)
    {
        Controller.getInstance().getDrawables().add(drawable);
    }
    default int getDrawPriority() {return 0;}
}
