package com.github.hcsp.multithread;

import java.util.concurrent.locks.ReentrantLock;

public class Boss2 {
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
        ReentrantLock lock = new ReentrantLock();
        Container2 container = new Container2(lock);

        Producer2 producer = new Producer2(container, lock);
        Consumer2 consumer = new Consumer2(container, lock);

        consumer.start();
        producer.start();

        producer.join();
        producer.join();
    }
}
