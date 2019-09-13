package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.stream.IntStream;

public class Consumer extends Thread {
    private final Object lock;
    private Container container;

    public Consumer(Object lock, Container container) {
        this.lock = lock;
        this.container = container;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            synchronized (lock) {
                while (container.getValue().isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Integer value = container.getValue().get();
                System.out.println("Consuming " + value);
                container.setValue(Optional.empty());

                lock.notify();
            }
        });
    }
}
