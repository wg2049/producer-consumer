package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer2 extends Thread {
    private Container2 container;
    private ReentrantLock lock;

    public Producer2(Container2 container, ReentrantLock lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (container.getValue().isPresent()) {
                    try {
                        container.getNotProducedYet().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer r = new Random().nextInt();
                container.setValue(Optional.of(r));
                System.out.println("Producing " + r);
                container.getNotConsumedYet().signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

/*

另外说一下自己对这种生产者和消费者模型实现方式的理解：

设置条件判断是因为 一方面在for循环中, synchronized和ReentrantLock作为可重入锁可重复加锁进入代码块, 但是需要先生产后才能消费, 未消费前也不能再生产, 所以当生产或消费条件不满足时, 需要令当前线程进入waiting

另一方面,是为了当前线程被中断和假唤醒时, 要继续进入waiting, 并且要用循环一直"盯"着.



*/