package multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSample {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        Runnable task1 = () -> {
            System.out.println("Task1 invoked by: " + Thread.currentThread().getName());
        };

        Runnable task2 = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task2 invoked by: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        pool.submit(task1);
        pool.submit(task2);

        try {
            pool.awaitTermination(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdownNow();
        }



    }

}
