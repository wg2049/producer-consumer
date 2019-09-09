package com.github.hcsp.multithread;


import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    public Consumer(int i, BlockingQueue<Integer> queue, BlockingQueue<Integer> signal) {
        this.i = i;
        this.queue = queue;
        this.signal = signal;
    }

    private int i;
    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signal;


    @Override
    public void run() {
        for (int j = 0; j < i; j++) {
            try {
                System.out.println("Consuming " + queue.take());
                signal.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


