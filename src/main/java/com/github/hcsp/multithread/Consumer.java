package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer extends Thread {
    private final BlockingDeque<Integer> queue;
    private final BlockingDeque<Integer> signalQueue;

    public Consumer(BlockingDeque<Integer> queue, BlockingDeque<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();
            try {
                System.out.println("Producing " + random);
                queue.put(random);
                signalQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
