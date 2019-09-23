package com.github.hcsp.multithread;

import java.util.Optional;

public class Consumer extends Thread {
    private Object lock;
    private Container container;

    public Consumer(Object lock, Container container) {
        this.lock = lock;
        this.container = container;
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
                System.out.println("Consuming " + container.getContainer().get());
                container.setContainer(Optional.empty());
                lock.notify();
            }
        }

    }
}
