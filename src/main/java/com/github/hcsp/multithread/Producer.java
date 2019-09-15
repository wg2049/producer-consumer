package com.github.hcsp.multithread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private final BlockingQueue<Integer> queue;
    private final BlockingDeque<Integer> signalQueue;

    public Producer(BlockingQueue<Integer> queue, BlockingDeque<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consuming " + queue.take());
                signalQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
