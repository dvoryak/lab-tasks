package com.company.thread_pool;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;

public class CustomThreadPool {

    private  boolean isRunning = true;
    private Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();

    public CustomThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            new Worker();
        }
    }

    public void execute(Runnable runnable) {
        tasks.offer(runnable);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask<T> futureTask = new FutureTask<T>(callable);
        tasks.offer(futureTask);
        return futureTask;
    }

    public <T> Optional<Future<T>> safeSubmit(Callable<T> callable) {
        return Optional.of(submit(callable));
    }


    public void shutdown() {
        isRunning = false;
    }

    private class Worker extends Thread {

        public Worker() {
            start();
        }

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
