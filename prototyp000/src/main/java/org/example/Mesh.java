package org.example;

import java.io.*;
import java.util.ArrayList;

public class Mesh
{
    private ArrayList<Vector3f> vertices;
    private ArrayList<Vector3> faces;

    public ArrayList<Vector3f> getVertices() {return vertices;}
    public void setVertices(ArrayList<Vector3f> vertices) {this.vertices = vertices;}
    public ArrayList<Vector3> getFaces() {return faces;}
    public void setFaces(ArrayList<Vector3> faces) {this.faces = faces;}

    public Mesh()
    {
        this.vertices = new ArrayList<>();
        this.faces = new ArrayList<>();
    }

    public static Mesh parseFile(String filePath)
    {
        File file = new File(PathParser.parse(filePath));
        Mesh mesh = new Mesh();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (line.startsWith("v "))
                {
                    String[] tokens = line.split("\\s+");   // \\s+ - white character
                    if (tokens.length != 4)
                    {
                        throw new IOException("Unexpected vertex line: " + line);
                    }
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    float z = Float.parseFloat(tokens[3]);
                    mesh.vertices.add(new Vector3f(x, y, z));
                }
                else if (line.startsWith("f "))
                {
                    String[] tokens = line.split("\\s+");
                    if (tokens.length != 4)
                    {
                        throw new IOException("Unexpected face line: " + line);
                    }
                    int a = Integer.parseInt(tokens[1]) - 1;
                    int b = Integer.parseInt(tokens[2]) - 1;
                    int c = Integer.parseInt(tokens[3]) - 1;
                    mesh.faces.add(new Vector3(a, b, c));
                }
                else
                {
                    throw new IOException("Unexpected line format: " + line);
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return mesh;
    }
}
