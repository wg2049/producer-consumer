package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Producer extends Thread {
    BlockingDeque<Integer> queue;
    BlockingDeque<Integer> signalqueue;

    public Producer(BlockingDeque<Integer> queue, BlockingDeque<Integer> signalqueue) {
        this.queue = queue;
        this.signalqueue = signalqueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            System.out.println("Producing " + r);
            try {
                queue.put(r);
                signalqueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
