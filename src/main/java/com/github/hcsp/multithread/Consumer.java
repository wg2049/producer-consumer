package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue<Integer> blockingQueue;
    private BlockingQueue<Integer> signal;

    public Consumer(BlockingQueue<Integer> blockingQueue, BlockingQueue<Integer> signal) {
        this.blockingQueue = blockingQueue;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consuming " + blockingQueue.take());
                signal.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
