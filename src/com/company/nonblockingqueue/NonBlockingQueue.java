package com.company.nonblockingqueue;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingQueue<E> {
    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(new Node<E>(null, null));
    private AtomicReference<Node<E>> tail = head;

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();

            if (curTail == tail.get()) {
                if (residue == null) /* A */ {
                    if (curTail.next.compareAndSet(null, newNode)) /* C */ {
                        tail.compareAndSet(curTail, newNode) /* D */ ;
                        return true;
                    }
                } else {
                    tail.compareAndSet(curTail, residue) /* B */;
                }
            }
        }
    }

    public E pull() {

        System.out.println(head.get().item);

       /* while(true) {
            E item = head.get().item;
            System.out.println(head.get().next);
            if(head.compareAndSet(head.get(),head.get().next.get())) {
                System.out.println(head.get());
                return item;
            }
        }*/

       return null;

    }

    public static void main(String[] args) {
        NonBlockingQueue queue = new NonBlockingQueue();

        queue.put(12);
        queue.put(3);
        queue.put(5);

        queue.pull();

        //System.out.println(queue.pull());
    }
}
