package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;

public class Producer extends Thread {
    private Container container;
    private Object lock;

    Producer(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int randomInt = new Random().nextInt();
                System.out.println("Producing " + randomInt);
                container.setValue(Optional.of(randomInt));

                lock.notify();
            }
        }
    }
}
