package com.company.task2;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /* CustomThreadPool poll = new CustomThreadPool(10);

        poll.execute(new RunnableTask());
        System.out.println("+");*/

    }
}

class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("+");
    }
}

class CallableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "hello";
    }
}
