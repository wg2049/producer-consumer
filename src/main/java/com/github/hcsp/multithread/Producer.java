package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    Container container;
    ReentrantLock lock;

    public Producer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                // 只要盘子里有东西 它就必须等待
                while (container.getValue().isPresent()) {
                    try {
                        container.getNotConsumedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 盘子是空的就生产
                int r = new Random().nextInt();
                System.out.println("Producing " + r);
                container.setValue(Optional.of(r));
                // 唤醒消费者
                container.getNotProducedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
