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

        try {
            int random = new Random().nextInt();
            System.out.println("Producing" + " " + random);
            queue.put(random);
            signal.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
