package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;

public class Producer extends Thread {
    private Container container;
    private final Object lock;

    Producer(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {

            synchronized (lock) {
                while (container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int random = new Random().nextInt();
                System.out.println("Producing" + " "+ random);
                container.setValue(Optional.of(random));
                lock.notify();
            }
    }
}
