package com.company.task2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CustomThreadPool {

    private volatile boolean isRunning;
    private Queue<Runnable> tasks = new ConcurrentLinkedDeque<>();

    public CustomThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            new Worker();
        }
    }

    public void execute(Runnable runnable) {
        tasks.offer(runnable);
    }

    public void shutdown() {
        isRunning = false;
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while(isRunning) {
                Runnable task = tasks.poll();
                if(task != null) {
                    task.run();
                }
            }
        }
    }
}
