package com.company.task3;

import com.company.task3.matrix.Matrix;
import com.company.task3.matrix.Matrixs;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Matrix m1 = Matrixs.generateRandomMatrix(1211,999);
        Matrix m2 = Matrixs.generateRandomMatrix(999,1231);



        Date date1 = new Date();
        Matrix mx1 = Matrixs.multiplyMatrix(m1, m2);
       // System.out.println(mx1);
        System.out.println(new Date().getTime() - date1.getTime());

        Date date = new Date();
        Matrix mx2 = Matrixs.paralelMultiplyMatrix(m1, m2);
        //System.out.println(mx2);
        System.out.println(new Date().getTime() - date.getTime());


        System.out.println(mx1.equals(mx2));

    }
}
