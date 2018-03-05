package com.company.synhronizers.exchanger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;

public class ExchangerSample {
    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();

        Job job1 = new Job(exchanger);
        Job job2 = new Job(exchanger);

        job1.start();
        job2.start();
    }
}

class Message {
    private String header;
    private String body;

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class Job extends Thread {
    private Exchanger<Message> exchanger;
    private Queue<Message> queue = new LinkedList<>();

    public Job(Exchanger<Message> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String header = this.getName() + " # " + i + "sam22sa1dk3f1g23s2";
            String body = "dsadk123jkjdsad9oasndom";

            queue.offer(new Message(header,body));
        }

        try {
            Message m1 = exchanger.exchange(queue.poll());
            System.out.println("Run: " + Thread.currentThread().getName() + " exchange message: " + m1.getHeader() + m1.getBody());

          /*  Message m2 = exchanger.exchange(queue.poll());
            System.out.println("Exchange: " + m1.getHeader());*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
