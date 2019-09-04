package com.github.hcsp.multithread;

import java.util.concurrent.BlockingDeque;

public class Consumer extends Thread {
    BlockingDeque<Integer> queue;
    BlockingDeque<Integer> signalqueue;

    public Consumer(BlockingDeque<Integer> queue, BlockingDeque<Integer> signalqueue) {
        this.queue = queue;
        this.signalqueue = signalqueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consuming " + queue.take());
                signalqueue.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
