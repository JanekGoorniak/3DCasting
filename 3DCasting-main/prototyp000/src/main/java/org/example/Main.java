package org.example;
import javax.swing.*;

public class Main
{
    public static JFrame window;

    public static void main(String[] args)
    {
        Vector3f vector = new Vector3f(1, 2, 3);
        vector.toMatrix();
        Matrix.multiply(Matrix.perspectiveProjectionMatrix(1, 2, 3, 4), vector.toMatrix());

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("3D Simulation");
        Controller controller = Controller.getInstance();
        window.setSize(800, 800);
        window.add(controller);
        controller.startThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}