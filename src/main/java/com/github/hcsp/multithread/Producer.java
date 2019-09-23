package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue blockingQueue;
    private BlockingQueue singleQueue;

    public Producer(BlockingQueue blockingQueue, BlockingQueue singleQueue) {
        this.blockingQueue = blockingQueue;
        this.singleQueue = singleQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Integer random = new Random().nextInt();
            try {
                System.out.println("Producing " + random);
                blockingQueue.put(random);
                singleQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
