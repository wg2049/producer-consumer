package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    private Container container;
    ReentrantLock lock;

    Producer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {

        try {
            lock.lock();
            while (container.getValue().isPresent()) {
                try {
                    container.getNotConsumedYet().await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int random = new Random().nextInt();
            System.out.println("Producing" + " " + random);
            container.setValue(Optional.of(random));
            container.getNotProducedYet().signal();
        } finally {
            lock.unlock();
        }
    }
}
