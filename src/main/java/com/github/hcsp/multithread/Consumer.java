package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
    private Lock lock;
    private Container container;
    private Condition containerNotValue;
    private Condition containerYetValue;

    public Consumer(Lock lock, Container container, Condition containerNotValue, Condition containerYetValue) {
        this.lock = lock;
        this.container = container;
        this.containerNotValue = containerNotValue;
        this.containerYetValue = containerYetValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (!container.getContainer().isPresent()) {
                    try {
                        containerNotValue.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming " + container.getContainer().get());
                container.setContainer(Optional.empty());
                containerYetValue.signal();
            } finally {
                lock.unlock();
            }
        }

    }
}
