package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue<Integer> queue;
    private BlockingQueue<Integer> signalQueue;

    public Producer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            try {
                System.out.println("Producing " + r);
                queue.put(r);
                signalQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
