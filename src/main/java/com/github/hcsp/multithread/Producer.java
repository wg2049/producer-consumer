package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class Producer extends Thread {
    private final Lock lock;
    private Container container;

    public Producer(Lock lock, Container container) {
        this.lock = lock;
        this.container = container;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 10).forEach(x -> {
            try{
                lock.lock();

                while (container.getValue().isPresent()) {
                    container.getNotConsumedYet().await();
                }

                Integer randomInt = new Random().nextInt();
                System.out.println("Producing " + randomInt);
                container.setValue(Optional.of(randomInt));
                container.getNotProducedYet().signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }
}
