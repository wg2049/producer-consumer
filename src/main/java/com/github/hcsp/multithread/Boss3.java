package com.github.hcsp.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Boss3 {
    public static void main(String[] args) throws InterruptedException {
        // 请实现一个生产者/消费者模型，其中：
        // 生产者生产10个随机的整数供消费者使用（随机数可以通过new Random().nextInt()获得）
        // 使得标准输出依次输出它们，例如：
        // Producing 42
        // Consuming 42
        // Producing -1
        // Consuming -1
        // ...
        // Producing 10086
        // Consuming 10086
        // Producing -12345678
        // Consuming -12345678
        BlockingQueue queue = new ArrayBlockingQueue(1);
        BlockingQueue signalQueue = new ArrayBlockingQueue(1);

        Producer3 producer = new Producer3(queue, signalQueue);
        Consumer3 consumer = new Consumer3(queue, signalQueue);

        producer.start();
        consumer.start();

        producer.join();
        producer.join();
    }
}