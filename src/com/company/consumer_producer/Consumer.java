package com.company.consumer_producer;

import java.util.Queue;

public class Consumer implements Runnable {

    private Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            if(queue.size() > 0) {
                synchronized (queue) {
                    Object remove = queue.remove();
                    System.out.println("Consumer gets item: " + remove);
                    try {
                        queue.notify();
                        Thread.sleep(100);
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
