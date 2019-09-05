package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    BlockingQueue<Integer> queue;
    Semaphore semaphore;

    public Producer(BlockingQueue<Integer> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt();
            System.out.println("Producing " + r);
            try {
                queue.put(r);
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
