package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
    Container container;
    Lock lock;

    public Consumer(Container container, Lock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                while (!container.getContainer().isPresent()) {
                    try {
                        container.getNotProducedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer r = container.getContainer().get();
                System.out.println("Consuming " + r);
                container.setContainer(Optional.empty());
                container.getNotConsumedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
