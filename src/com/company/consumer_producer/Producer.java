package com.company.consumer_producer;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private Queue queue; // v ?

    public Producer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (queue.size() < 100) {
                synchronized (queue) {
                    int nextInt = ThreadLocalRandom.current().nextInt(0,100);
                    System.out.println("Producer produces the item: " + nextInt);
                    queue.add(nextInt);
                    try {
                        Thread.currentThread().sleep(1000);
                        queue.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (queue) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
