package org.example;

public interface Updatable
{
    void update();
    default void addToUpdatables(Updatable updatable)
    {
        Controller.getInstance().getUpdatables().add(updatable);
    }
    default int getUpdatePriority() {return 0;}
}
