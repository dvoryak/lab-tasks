package com.company.task3;

import com.company.task3.matrix.Matrix;
import com.company.task3.matrix.Matrixs;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Matrix m1 = Matrixs.generateRandomMatrix(3,2);
        Matrix m2 = Matrixs.generateRandomMatrix(2,3);



        Date date1 = new Date();
        System.out.println("____SINGLE____");
        Matrix mx1 = Matrixs.multiplyMatrix(m1, m2);
       // System.out.println(mx1);
        System.out.println(new Date().getTime() - date1.getTime());

        Date date = new Date();
        System.out.println("____PARaLEL____");
        Matrix mx2 = Matrixs.paralelMultiplyMatrix(m1, m2);
        //System.out.println(mx2);
        System.out.println(new Date().getTime() - date.getTime());


        System.out.println(mx1.equals(mx2));

    }
}
