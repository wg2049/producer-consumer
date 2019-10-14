package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer2 extends Thread {
    private Container2 container;
    private ReentrantLock lock;

    public Producer2(Container2 container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (container.getValue().isPresent()) {
                    try {
                        container.getNotProducedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer r = new Random().nextInt();
                container.setValue(Optional.of(r));
                System.out.println("Producing " + r);
                container.getNotConsumedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
