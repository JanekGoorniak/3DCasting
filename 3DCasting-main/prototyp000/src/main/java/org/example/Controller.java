package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Controller extends JPanel implements Runnable
{
    private Thread mainThread;
    private int simulationTicksLimit = 30;
    private int frameTicksLimit = 999;
    private static Controller instance;

    private PriorityQueue<Drawable> drawables;
    private PriorityQueue<Updatable> updatables;

    public PriorityQueue<Drawable> getDrawables() {return drawables;}
    public PriorityQueue<Updatable> getUpdatables() {return updatables;}

    Test test;

    private Controller()
    {
        instance = this;
        drawables = new PriorityQueue<>(Comparator.comparingInt(Drawable::getDrawPriority));
        updatables = new PriorityQueue<>(Comparator.comparingInt(Updatable::getUpdatePriority));
        test = new Test();
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
    }

    public void startThread()
    {
        mainThread = new Thread(this);
        mainThread.start();
    }


    @Override
    public void run()       // MAIN LOOP
    {
        long logicInterval = 1000000000 / simulationTicksLimit;   // logic ns
        long drawInterval = 1000000000 / frameTicksLimit;     // draw ns
        int maxLogicUpdatesPerFrame = Math.max(frameTicksLimit / simulationTicksLimit, 1);

        long lastTime = System.nanoTime();
        long accumulator = 0;
        long maxAccumulator = logicInterval * maxLogicUpdatesPerFrame;
        while (mainThread != null)
        {
            long currentTime = System.nanoTime();
            long deltaT = currentTime - lastTime;
            lastTime = currentTime;

            accumulator += deltaT;

            if (accumulator > maxAccumulator)
            {
                accumulator = maxAccumulator;
            }

            int logicUpdates = 0;

            while (accumulator >= logicInterval && logicUpdates < maxLogicUpdatesPerFrame)
            {
                updateLogic();
                accumulator -= logicInterval;
                logicUpdates++;
            }
            repaint();
            try
            {
                long sleepTime = drawInterval - (System.nanoTime() - currentTime);
                if (sleepTime > 0)
                {
                    Thread.sleep(sleepTime / 1000000);  // ns -> ms
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void updateLogic()    // UPDATE LOGIC
    {
        while (!updatables.isEmpty())
        {
            Updatable updatable = updatables.peek();
            updatable.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        setBackground(Color.BLACK);
        /*
        while (!drawables.isEmpty())
        {
            Drawable drawable = drawables.peek();
            drawable.draw(g2);
        }
         */

        for (Drawable drawable : drawables)
        {
            drawable.draw(g2);
        }
        g2.dispose();
    }
}
