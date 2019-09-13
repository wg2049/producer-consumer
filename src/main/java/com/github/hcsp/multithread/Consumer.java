package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class Consumer extends Thread {
    private final Lock lock;
    private Container container;

    public Consumer(Lock lock, Container container) {
        this.lock = lock;
        this.container = container;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            try {
                lock.lock();

                while (!container.getValue().isPresent()) {
                    container.getNotProducedYet().await();
                }

                Integer value = container.getValue().get();
                System.out.println("Consuming " + value);
                container.setValue(Optional.empty());
                container.getNotConsumedYet().signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }
}
