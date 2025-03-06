package org.example;

public class Matrix
{
    int columns;
    int rows;

    float[][] content;

    Matrix(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
        this.content = new float[rows][columns];
        System.out.println("New matrix created, columns: " + columns + ", rows: " + rows);
    }

    public static Matrix multiply(Matrix matA, Matrix matB)
    {
        if (matA == null || matB == null)
        {
            throw new IllegalArgumentException("Matrices cannot be null");
        }

        if (matA.columns != matB.rows)
        {
            throw new IllegalArgumentException("Invalid matrix sizes for multiplication");
        }

        Matrix result = new Matrix(matB.columns, matA.rows);
        for (int i = 0; i < matA.rows; i++)
        {
            System.out.println("i: " + i);
            for (int j = 0; j < matB.columns; j++)
            {
                System.out.println("j: " + j);
                for (int k = 0; k < matB.rows; k++)
                {
                    System.out.println("k: " + k);
                    result.content[i][j] += matA.content[i][k] + matB.content[k][j];
                }
            }
        }
        return result;
    }

    public static Matrix perspectiveProjectionMatrix(float aspect, float fov, float far, float near)
    {
        Matrix ppm = new Matrix(4, 4);
        ppm.content[0][0] = (float) (1/(aspect * Math.tan(fov/2)));
        ppm.content[1][1] = (float) (1/Math.tan(fov/2));
        ppm.content[2][2] = -(far + near) / (far - near);
        ppm.content[2][3] = -(2*far*near)/(far-near);
        ppm.content[3][2] = -1;
        return ppm;
    }
}
