package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread {
    Container container;
    Lock lock;

    public Producer(Container container, Lock lock) {
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
                        container.getNotConsumedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int r = new Random().nextInt();
                System.out.println("Producing " + r);
                container.setContainer(Optional.of(r));
                container.getNotProducedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
