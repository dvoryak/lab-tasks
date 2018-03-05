package com.company.synhronizers.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SemaphoreRWStrategy {

    public static void main(String[] args) {
        Semaphore writing = new Semaphore(11);
        Semaphore reading = new Semaphore(20);

        List<Integer> resources = new ArrayList<>();

        List<Thread> pool = new ArrayList<>();


        for (int i = 0; i < 100; i++) {

            Thread reader = new Thread(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    reading.acquire();
                    System.out.println(Thread.currentThread().getName() + " invoke " + resources.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reading.release();
                }
            });
            reader.setName("Reader#i");
            pool.add(reader);
        }

        for (int i = 0; i < 100; i++) {
            int val = i;
            Thread writer = new Thread(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    writing.acquire();
                    resources.add(val);
                    System.out.println(Thread.currentThread().getName() + " writing " + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writing.release();
                }
            });
            writer.setName("Writer#i");
            pool.add(writer);

        }





        pool.forEach(Thread::start);

        pool.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Final result: " + resources.size());
    }

}
