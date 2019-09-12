package com.github.hcsp.multithread.method2;

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
                while (!container.getContainer().isPresent()) {
                    try {
                        container.getNotBeenConsumer().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer random = container.getContainer().get();
                container.setContainer(Optional.empty());
                System.out.println("Consuming " + random);
                container.getNotBeenProduct().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

