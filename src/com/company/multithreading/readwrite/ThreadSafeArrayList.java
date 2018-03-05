package com.company.readwrite;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeArrayList<T> {
    private ArrayList<T> list;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public ThreadSafeArrayList(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public void add(T e) {
        writeLock.lock();
        try {
            System.out.println("Element: " + e + " was added by: " + Thread.currentThread().getName());
            list.add(e);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        readLock.lock();
        try {
            System.out.println("Extract by: " + Thread.currentThread().getName());
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
