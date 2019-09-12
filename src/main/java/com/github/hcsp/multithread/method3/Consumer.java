package com.github.hcsp.multithread.method3;

import java.util.concurrent.BlockingDeque;

public class Consumer extends Thread {
    BlockingDeque<Integer> resultSet;
    BlockingDeque<Integer> signal;

    public Consumer(BlockingDeque<Integer> resultSet, BlockingDeque<Integer> signal) {
        this.resultSet = resultSet;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Consuming " + resultSet.take());
                signal.put(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
