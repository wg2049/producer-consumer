package com.github.hcsp.multithread.method3;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Producer extends Thread {
    BlockingDeque<Integer> resultSet;
    BlockingDeque<Integer> signal;

    public Producer(BlockingDeque<Integer> resultSet, BlockingDeque<Integer> signal) {
        this.resultSet = resultSet;
        this.signal = signal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();
            System.out.println("Producing " + random);
            try {
                resultSet.put(random);
                signal.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
