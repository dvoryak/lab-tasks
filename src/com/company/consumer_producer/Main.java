package com.company.consumer_producer;

import java.util.LinkedList;
import java.util.Queue;

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
