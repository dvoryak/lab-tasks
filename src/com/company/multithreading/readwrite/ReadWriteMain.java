package com.company.readwrite;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.*;

public class ReadWriteMain {

    public static void main(String[] args) {
	// write your code here
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>(100);
        list.add(0);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.get(0);
                try {
                    sleep(ThreadLocalRandom.current().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            final int a = 12;

            new Thread(() -> {
                list.add(a);
                try {
                    sleep(ThreadLocalRandom.current().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
