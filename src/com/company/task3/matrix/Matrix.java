package com.company.task3.matrix;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Arrays;

public class Matrix {
    private int n;
    private int m;
    private int[][] data;

    public Matrix(int[][] data) {
        if(data.length < 1) throw new IllegalArgumentException();
        this.n = data.length;
        this.m = data[0].length;
        this.data = Arrays.copyOf(data,data.length);
    }

    public int getRows() {
        return n;
    }

    public int getColumns() {
        return m;
    }

    public int cell(int n, int m) {
        return data[n][m];
    }

    public void setCell(int n, int m, int value) {
        data[n][m] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix = (Matrix) o;

        if (n != matrix.n) return false;
        if (m != matrix.m) return false;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = n;
        result = 31 * result + m;
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(data[i][j])
                    .append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
