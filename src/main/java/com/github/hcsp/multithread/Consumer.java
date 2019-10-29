package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signalQueue;

    public Consumer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int v = 0;
            try {
                v = queue.take();
                signalQueue.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consuming " + v);
        }
    }
}
