package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Consumer extends Thread {
    private BlockingQueue<Integer> queue;
    private BlockingQueue<Integer> signalQueue;

    public Consumer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            try {
                System.out.println("Consuming " + queue.take());
                signalQueue.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
