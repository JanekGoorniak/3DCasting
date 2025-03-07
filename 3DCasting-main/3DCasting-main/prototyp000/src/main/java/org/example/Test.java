package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Test implements Drawable
{
    public static Mesh testCubeMesh;    // test purposes

    public Test()
    {
        addToDrawables(this);
        testCubeMesh = new Mesh();

        testCubeMesh.getVertices().add(new Vector3f(-1, -1, -1)); // 0
        testCubeMesh.getVertices().add(new Vector3f( 1, -1, -1)); // 1
        testCubeMesh.getVertices().add(new Vector3f( 1,  1, -1)); // 2
        testCubeMesh.getVertices().add(new Vector3f(-1,  1, -1)); // 3
        testCubeMesh.getVertices().add(new Vector3f(-1, -1,  1)); // 4
        testCubeMesh.getVertices().add(new Vector3f( 1, -1,  1)); // 5
        testCubeMesh.getVertices().add(new Vector3f( 1,  1,  1)); // 6
        testCubeMesh.getVertices().add(new Vector3f(-1,  1,  1)); // 7

        // Front (z = 1)
        testCubeMesh.getFaces().add(new Vector3(4, 5, 6));
        testCubeMesh.getFaces().add(new Vector3(6, 7, 4));
        // Back (z = -1)
        testCubeMesh.getFaces().add(new Vector3(0, 1, 2));
        testCubeMesh.getFaces().add(new Vector3(2, 3, 0));
        // Left (x = -1)
        testCubeMesh.getFaces().add(new Vector3(0, 4, 7));
        testCubeMesh.getFaces().add(new Vector3(7, 3, 0));
        // Right (x = 1)
        testCubeMesh.getFaces().add(new Vector3(1, 5, 6));
        testCubeMesh.getFaces().add(new Vector3(6, 2, 1));
        // Top (y = 1)
        testCubeMesh.getFaces().add(new Vector3(3, 2, 6));
        testCubeMesh.getFaces().add(new Vector3(6, 7, 3));
        // Bottom (y = -1)
        testCubeMesh.getFaces().add(new Vector3(0, 1, 5));
        testCubeMesh.getFaces().add(new Vector3(5, 4, 0));
    }
    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        for(Vector3 element : testCubeMesh.getFaces()) //we need METH
        {

        }
        //System.out.println("draw");
        g.dispose();
    }
}