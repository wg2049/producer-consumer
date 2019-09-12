package com.github.hcsp.multithread.method3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Third method to solve this problem
 * BlockingDeque
 */


public class Boss {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Integer> resultSet = new LinkedBlockingDeque<>(1);
        BlockingDeque<Integer> signal = new LinkedBlockingDeque<>(1);
        Producer producer = new Producer(resultSet, signal);
        Consumer consumer = new Consumer(resultSet, signal);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
