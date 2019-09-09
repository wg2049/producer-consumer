package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;

public class Producer extends Thread {
    private int i;
    ShareData container;
    Object lock;

    public Producer(ShareData container, Object lock, int i) {
        this.container = container;
        this.lock = lock;
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < i; j++) {
            synchronized (lock) {
                while (container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int r = new Random().nextInt();
                System.out.println("Producing " + r);
                container.setValue(Optional.of(r));
                lock.notify();
            }
        }
    }

    //方法二的run部分
//    @Override
//    public void run() {
//        for (int j = 0; j < i; j++) {
//            try {
//                relock.lock();
//                while (container.getValue().isPresent()) {
//                    try {
//                        container.getNotConsumedYet().await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                    int r = new Random().nextInt();
//                    System.out.println("Producing "+r);
//                    container.serValue(Optional.of(r));
//                    container.getNotProducedYer().signal();
//            } finally {
//                relock.unlock();
//            }
//        }
}
