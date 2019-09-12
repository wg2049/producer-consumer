package com.github.hcsp.multithread.method2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Second method to solve this problem
 * reentrantLock/condition
 */

public class Boss {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Container container = new Container(lock);
        Producer producer = new Producer(container, lock);
        Consumer consumer = new Consumer(container, lock);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
