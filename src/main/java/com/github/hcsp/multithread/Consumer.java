package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    private Container container;
    ReentrantLock lock;

    Consumer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (!container.getValue().isPresent()) {
                try {
                    container.getNotProducedYet().await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer value = container.getValue().get();
            container.setValue(Optional.empty());
            System.out.println("Consuming" + " " + value);
            container.getNotProducedYet().signal();
        } finally {
            lock.unlock(); //无论发生什么都要把锁释放掉
        }
    }
}
