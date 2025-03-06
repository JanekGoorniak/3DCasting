package org.example;

public class Vector3f
{
    float x, y, z;

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Matrix toMatrix()
    {
        Matrix matrix = new Matrix(1, 4);
        matrix.content[0][0] = this.x;
        matrix.content[1][0] = this.y;
        matrix.content[2][0] = this.z;
        matrix.content[3][0] = 1;
        return matrix;
    }
}
