package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue blockingQueue;
    private BlockingQueue singleQueue;

    public Consumer(BlockingQueue blockingQueue, BlockingQueue singleQueue) {
        this.blockingQueue = blockingQueue;
        this.singleQueue = singleQueue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consuming " + blockingQueue.take());
                singleQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
