package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;

public class Producer extends Thread {
    private Object lock;
    private Container container;

    public Producer(Object lock, Container container) {
        this.lock = lock;
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (container.getContainer().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer random = new Random().nextInt();
                container.setContainer(Optional.of(random));
                System.out.println("Producing " + random);
                lock.notify();
            }
        }

    }
}
