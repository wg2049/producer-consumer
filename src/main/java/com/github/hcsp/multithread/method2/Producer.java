package com.github.hcsp.multithread.method2;

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
                while (container.getContainer().isPresent()) {
                    try {
                        container.getNotBeenProduct().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer random = new Random().nextInt();
                System.out.println("Producing " + random);
                container.setContainer(Optional.of(random));
                container.getNotBeenConsumer().signal();
            } finally {
                lock.unlock();
            }
        }

    }
}

