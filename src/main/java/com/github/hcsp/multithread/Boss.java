package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class Boss {
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
        for (int i = 0; i < 10; i++) {

            BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(1); //接口不能够直接实体化，只能用实现，容量为1
            BlockingQueue<Integer> signal = new LinkedBlockingDeque<>(1); //仅用于传递信号

            Producer producer = new Producer(queue, signal);
            Consumer consumer = new Consumer(queue, signal);

            producer.start();
            consumer.start();

            producer.join();
            producer.join();
        }
    }
}
