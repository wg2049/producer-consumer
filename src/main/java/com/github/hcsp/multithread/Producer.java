package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    Container container;
    ReentrantLock lock;

    public Producer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();

                while (container.getValue().isPresent()) {
                    try {
                        container.getNotConsumdYet().await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
                int r = new Random().nextInt();
                container.setValue(Optional.of(r));
                System.out.println("Producing " + r);
                container.getNotProducedYet().signal();
            } finally {
               lock.unlock();
            }
        }
    }
}
