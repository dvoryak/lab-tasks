package com.company.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CustomThreadPool poll = new CustomThreadPool(10);

        poll.execute(new RunnableTask());
        List<Future> rez = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            rez.add(poll.submit(new CallableTask("name-" + i)));
        }

        rez.stream().forEach((x) -> {
            try {
                System.out.println(x.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });


        Optional<Future<String>> pavel = poll.safeSubmit(new CallableTask("Pavel"));
        if (pavel.isPresent()) {
            System.out.println("Is present");
            System.out.println(pavel.get().get());
        }


        poll.shutdown();
    }
}

class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("+");
    }
}

class CallableTask implements Callable<String> {
    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "hello " + name;
    }
}
