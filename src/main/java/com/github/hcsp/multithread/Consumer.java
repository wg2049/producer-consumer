package com.github.hcsp.multithread;

import java.util.Optional;

public class Consumer extends Thread {
    Container container;
    Object lock;

    public Consumer(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (!container.getContainer().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer r = container.getContainer().get();
                System.out.println("Consuming " + r);
                container.setContainer(Optional.empty());
                lock.notify();
            }
        }
    }
}
