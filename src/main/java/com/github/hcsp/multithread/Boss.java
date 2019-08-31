package com.github.hcsp.multithread;

public class Boss {
    public static void main(String[] args) throws InterruptedException {
        // 请实现一个生产者/消费者模型，其中：
        // 生产者生产10个元素供消费者使用
        // 使得标准输出依次输出：
        // Producing 1
        // Consuming 1
        // Producing 2
        // Consuming 2
        // ...
        // Producing 10
        // Consuming 10

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();

        producer.join();
        producer.join();
    }
}
