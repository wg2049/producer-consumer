package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread {
    private Lock lock;
    private Container container;
    private Condition containerNotValue;
    private Condition containerYetValue;

    public Producer(Lock lock, Container container, Condition containerNotValue, Condition containerYetValue) {
        this.lock = lock;
        this.container = container;
        this.containerNotValue = containerNotValue;
        this.containerYetValue = containerYetValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (container.getContainer().isPresent()) {
                    containerYetValue.await();
                }
                Integer random = new Random().nextInt();
                container.setContainer(Optional.of(random));
                System.out.println("Producing " + random);
                containerNotValue.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
