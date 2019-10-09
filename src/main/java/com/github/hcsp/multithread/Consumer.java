package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    Container container;
    ReentrantLock lock;

    public Consumer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                while (!container.getValue().isPresent()) {
                    try {
                        container.getNotProducedYet().await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
                Integer value = container.getValue().get();
                container.setValue(Optional.empty());
                System.out.println("Consuming " + value);

                container.getNotConsumdYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
