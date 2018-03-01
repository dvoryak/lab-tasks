package com.company.task1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Queue queue = new LinkedList();

        Thread consumer = new Thread(new Consumer(queue));
        Thread producer = new Thread(new Producer(queue));

        consumer.start();
        producer.start();

    }
}
