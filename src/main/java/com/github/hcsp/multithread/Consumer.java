package com.github.hcsp.multithread;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    BlockingQueue<Integer> queue;
    BlockingQueue<Integer> signal;


    public Consumer(BlockingQueue<Integer> queue, BlockingQueue<Integer> signal) {
        this.queue = queue;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Integer take = null;
            try {
                take = queue.take();
                System.out.println("Consuming " + take);
                //发送消息继续生产
                signal.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
