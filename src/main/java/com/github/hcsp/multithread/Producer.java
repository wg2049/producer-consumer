package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class Producer extends Thread {
    private final Object lock;
    private Container container;

    public Producer(Object lock, Container container) {
        this.lock = lock;
        this.container = container;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            synchronized (lock) {
                while (container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Integer randomInt = new Random().nextInt();
                System.out.println("Produced: " + randomInt);
                container.setValue(Optional.of(randomInt));

                lock.notify();
            }
        });
    }
}
