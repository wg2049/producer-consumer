package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer3 extends Thread {
    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signalQueue;

    public Producer3(BlockingQueue<Integer> queue, BlockingQueue<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            System.out.println("Producing " + r);
            try {
                queue.put(r);
                signalQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
