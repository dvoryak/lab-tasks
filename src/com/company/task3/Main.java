package com.company.task3;

import com.company.task3.matrix.Matrix;
import com.company.task3.matrix.Matrixs;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Matrix m1 = Matrixs.generateRandomMatrix(4441,888);
        Matrix m2 = Matrixs.generateRandomMatrix(888,1444);


        Date date = new Date();
        System.out.println("____SINGLE____");
        Matrix mx1 = Matrixs.multiplyMatrix(m1, m2);
        System.out.println(new Date().getTime() - date.getTime());

        date = new Date();
        System.out.println("____PARaLEL____");
        Matrix mx2 = Matrixs.multiplyMatrix(m1, m2);
        System.out.println(new Date().getTime() - date.getTime());

        System.out.println(mx1.equals(mx2));

    }
}
