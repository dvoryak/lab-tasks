package com.company.matrixmultiply_single_paralel.matrix;

import java.util.*;
import java.util.concurrent.*;

public class Matrixs {

    public static Matrix generateRandomMatrix(int n, int m) {
        int date[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                date[i][j] = new Random().nextInt(100);
            }
        }

        return new Matrix(date);
    }

    public static Matrix generateEmptyMatrix(int n, int m) {
        return new Matrix(new int[n][m]);
    }


    /**
     * Multiple matrix
     *
     * 3 1
     * 2 4
     * 6 2
     *
     * 2 4 3
     * 2 4 5
     *
     *
     * @param m1
     * @param m2
     * @return
     */
    public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        System.out.println("____SINGLE____");
        if(m1.getColumns() != m2.getRows()) throw new IllegalArgumentException();

        Matrix m = generateEmptyMatrix(m1.getRows(),m2.getColumns());

        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                int sum = 0;
                for (int k = 0; k < m1.getColumns(); k++) {
                    sum = sum + m1.cell(i,k) * m2.cell(k,j);
                }
                m.setCell(i,j,sum);
            }
        }

        return m;
    }

    public static Matrix paralelMultiplyMatrix(Matrix m1, Matrix m2) throws ExecutionException, InterruptedException {
        if(m1.getColumns() != m2.getRows()) throw new IllegalArgumentException();
        //CustomThreadPool pool = new CustomThreadPool(4);
         ExecutorService pool = ForkJoinPool.commonPool();
        System.out.println("____PARaLEL____");

        List<Future<List<Integer>>> subs = new ArrayList<>();

        for (int i = 0; i < m1.getRows(); i++) {
            subs.add(pool.submit(new Task(m1,m2,i)));
        }


        Matrix rez = generateEmptyMatrix(m1.getRows(),m2.getColumns());

        for (int i = 0; i < rez.getRows(); i++) {
            List<Integer> vec = subs.get(i).get();
            for (int j = 0; j < rez.getColumns(); j++) {
                rez.setCell(i,j,vec.get(j));
            }
        }


        pool.shutdown();

        return rez;
    }


    /**
     *
     * 2 4 3
     * 2 4 5
     *
     * 3 1
     * 2 4
     * 6 2
     *
     * ? ?
     * ? ?
     *
     */
    private static final class Task implements Callable<List<Integer>> {

        private Matrix m1;
        private Matrix m2;
        private int tn;

        public Task(Matrix m1, Matrix m2, int tn) {
            this.m1 = m1;
            this.m2 = m2;
            this.tn = tn;
        }


        // m1(n,m) * m2(m,k)
        @Override
        public List<Integer> call() throws Exception {
            List<Integer> list = new ArrayList<>();
            int sum = 0;
            for (int k = 0; k < m2.getColumns(); k++) {
                sum = 0;
                for (int i = 0; i < m1.getColumns(); i++) {
                    sum = sum + m1.cell(tn, i) * m2.cell(i, k);
                }
                list.add(sum);
            }
            return list;
        }
    }
}
