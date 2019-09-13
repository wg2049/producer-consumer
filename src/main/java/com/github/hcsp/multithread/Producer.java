package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Producer extends Thread {

    private BlockingQueue<Integer> queue;
    private BlockingQueue<Integer> signalQueue;

    public Producer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signalQueue) {
        this.queue = queue;
        this.signalQueue = signalQueue;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            try {
                Integer randomInt = new Random().nextInt();
                System.out.println("Producing " + randomInt);
                queue.put(randomInt);
                signalQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
