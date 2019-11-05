package com.github.hcsp.multithread;

import java.util.Optional;

public class Consumer extends Thread {
    private Container container;
    private Object lock;

    Consumer(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (!container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int randomInt = container.getValue().get();
                System.out.println("Consuming " + randomInt);
                container.setValue(Optional.empty());
                lock.notify();
            }

        }
    }
}
