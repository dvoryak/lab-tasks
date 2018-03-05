package com.company.starvation;



import java.util.ArrayList;
import java.util.List;

/**
 * Priority starvation isn't working on MacOSX
 */
public class Starvation {

    private volatile static boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread(new Worker(), "Rich"));
        }
        list.forEach((x)->{x.setPriority(10); x.start();});

        Thread w4 = new Thread(new Worker(),"Poor");


        w4.setPriority(1);


        w4.start();

        Thread.sleep(1000);
        isRunning = false;
    }

    private static class Worker implements Runnable {

        private int counter = 0;

        @Override
        public void run() {
            while (isRunning) {
                counter++;
            }
            System.out.println(Thread.currentThread().getName() + " count: " + counter);
        }
    }
}
