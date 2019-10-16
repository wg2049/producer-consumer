package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue<Integer> blockingQueue;
    private BlockingQueue<Integer> signal;

    public Producer(BlockingQueue<Integer> blockingQueue, BlockingQueue<Integer> signal) {
        this.blockingQueue = blockingQueue;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            System.out.println("Producing " + r);
            try {
                blockingQueue.put(r);
                signal.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

