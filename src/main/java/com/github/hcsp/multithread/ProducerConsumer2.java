package com.github.hcsp.multithread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Container2 container = new Container2(lock);
        Producer producer = new Producer(lock, container);
        Consumer consumer = new Consumer(lock, container);

        producer.start();
        consumer.start();

        producer.join();
        producer.join();
    }

    public static class Producer extends Thread {
        private ReentrantLock lock;
        private Container2 container;

        public Producer(ReentrantLock lock, Container2 container) {
            this.lock = lock;
            this.container = container;
        }

        //这种方式与synchronized方式的一个不同在于，使用Condition可以精确控制你想唤醒哪一个在等待的线程
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (container.isPresent()) {
                        container.getNotConsumedYet().await();
                    }
                    int r = new Random().nextInt();
                    System.out.println("Producing " + r);
                    container.put(r);
                    container.getNotProducedYet().signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private ReentrantLock lock;
        private Container2 container;

        public Consumer(ReentrantLock lock, Container2 container) {
            this.lock = lock;
            this.container = container;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (!container.isPresent()) {
                        container.getNotProducedYet().await();
                    }
                    System.out.println("Consuming " + container.take());
                    container.getNotConsumedYet().signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
