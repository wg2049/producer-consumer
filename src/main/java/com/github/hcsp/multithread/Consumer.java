package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    Container container;
    ReentrantLock lock;

    public Consumer(Container container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                // 只要盘子是空的 它就等待
                while (!container.getValue().isPresent()) {
                    try {
                        container.getNotProducedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 盘子不是空的就消费
                Integer value = container.getValue().get();
                // 清空盘子的内容
                container.setValue(Optional.empty());
                System.out.println("Consuming " + value);

                // 唤醒生产者
                container.getNotConsumedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
