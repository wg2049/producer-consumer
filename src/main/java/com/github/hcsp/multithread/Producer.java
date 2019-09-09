package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private int i;
    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signal;

    public Producer(int i, BlockingQueue<Integer> queue, BlockingQueue<Integer> signal) {
        this.i = i;
        this.queue = queue;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int j = 0; j < i; j++) {
            int r = new Random().nextInt();
            System.out.println("Producing " + r);
            try {
                queue.put(r);
                signal.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
