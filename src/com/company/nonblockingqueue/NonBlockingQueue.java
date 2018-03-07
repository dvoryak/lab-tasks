package com.company.nonblockingqueue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonBlockingQueue<E> {

    private AtomicInteger size = new AtomicInteger(0);

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private Node<E> nullNode = new Node<>(null, null);
    private AtomicReference<Node<E>> head = new AtomicReference<>(nullNode);
    private AtomicReference<Node<E>> tail = new AtomicReference<>(nullNode);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);

        while (true) {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();
            if (curTail == tail.get()) {
                if (residue == null) {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode);
                        size.incrementAndGet();
                        return true;
                    }
                } else {
                    tail.compareAndSet(curTail, residue);
                }
            }
        }
    }

    public E pull() {
        E value = null;
        while (true) {
            Node<E> currHead = head.get();
            Node<E> currTail = tail.get();
            Node<E> next = currHead.next.get();

            if (head.get() == currHead) {
                if (currHead == currTail) {
                    if (next == null) {
                        throw new NoSuchElementException();
                    }
                    tail.compareAndSet(currTail, next);
                } else {
                    value = next.item;
                    if (head.compareAndSet(currHead, next)) {
                        break;
                    }
                }
            }

        }

        size.decrementAndGet();
        return value;


    }

    public int size() {
        return size.get();
    }

    public static void main(String[] args) {
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        // 5_000_000 of put operation
        List<Thread> producer = Stream.generate(() -> new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                queue.put(ThreadLocalRandom.current().nextInt(100));
            }
        })).limit(10).collect(Collectors.toList());
        producer.forEach(Thread::start);
        producer.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 1_000_000 of pull operation
        List<Thread> consumer = Stream.generate(() -> new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                queue.pull();
            }
        })).limit(10).collect(Collectors.toList());
        consumer.forEach(Thread::start);
        consumer.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println(queue.size());

    }
}
