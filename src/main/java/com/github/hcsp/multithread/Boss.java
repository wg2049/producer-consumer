package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Boss {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue blockingQueue = new ArrayBlockingQueue(1);
        BlockingQueue singleQueue =  new ArrayBlockingQueue(1);
        Container container = new Container(Optional.empty());
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

        Producer producer = new Producer(blockingQueue, singleQueue);
        Consumer consumer = new Consumer(blockingQueue, singleQueue);

        producer.start();
        consumer.start();

        producer.join();
        producer.join();
    }
}
