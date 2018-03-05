package com.company.nonblockingqueue;

import java.util.concurrent.atomic.AtomicReference;

public class SimpleQueue<E> {

    private class Node<E> {
        E item;
        AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    /*private Node<E> head = null;
    private Node<E> tail = null;*/

    private AtomicReference<Node<E>> head = new AtomicReference<>(new Node<>(null,null));
    private AtomicReference<Node<E>> tail = head;

    private int size;

    public void push(E item) {
        Node<E> newNode = new Node<>(item, null);


        while(true) {
            Node<E> currTail = tail.get();

            if(tail.compareAndSet(currTail,newNode)) {

            }
        }

        /* if(head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        size++;

        tail = newNode;*/

    }
/*
    public E pull() {
        if(isEmpty()) {
            return null;
        }

        E item = this.head.item;
        head = head.next;

        if(head == null) {
            tail = null;
        }

        size--;

        return item;
    }*/

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
