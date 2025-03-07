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
        this.content = new float[columns][rows];
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

        Matrix result = new Matrix(matB.rows, matA.columns);
        for (int i = 0; i < matB.rows; i++)
        {
            for (int j = 0; j < matA.columns; j++)
            {
                for (int k = 0; k < matB.columns; k++)
                {
                    result.content[i][j] += matB.content[i][k] + matA.content[k][j];
                }
            }
        }
        return result;
    }
}
