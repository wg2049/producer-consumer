package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;


public class Consumer extends Thread {

    BlockingQueue<Integer> queue; //单独的一个BlockingQueue消费者取出后就马上进行生产，导致没消费完成就已经完成第二次生产
    BlockingQueue<Integer> signal; //需要一个锁来控制生产者的速度

    public Consumer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signal) {
        this.queue = queue;
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            System.out.println("Consuming" + " " + queue.take());
            signal.put(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
