package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Boss {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition containerNotValue = lock.newCondition();
        final Condition containerYetValue = lock.newCondition();

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

        Producer producer = new Producer(lock, container, containerNotValue, containerYetValue);
        Consumer consumer = new Consumer(lock, container, containerNotValue, containerYetValue);

        producer.start();
        consumer.start();

        producer.join();
        producer.join();
    }
}
