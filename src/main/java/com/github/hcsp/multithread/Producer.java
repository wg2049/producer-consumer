package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signal;

    public Producer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signal) {
        this.queue = queue;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();
            System.out.println("Producing " + random);
            try {
                queue.put(random);
                signal.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
