package com.github.hcsp.multithread;

import java.util.Optional;
//import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    private int i;
    private Object lock;
    private ShareData container;
    //第二种方法的可重入锁
//    private ReentrantLock relock = null;

    public Consumer(ShareData container, Object lock, int i) {
        this.i = i;
        this.container = container;
        this.lock = lock;
    }

//    public Consumer(ShareData container, ReentrantLock lock, int i) {
//        this.i = i;
//        this.container = container;
//        this.relock = lock;
//    }


    @Override
    public void run() {
        for (int j = 0; j < i; j++) {
            synchronized (lock) {
                while (!container.getValue().isPresent()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer value = container.getValue().get();
                container.setValue(Optional.empty());
                System.out.println("Consuming " + value);
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
//                while (!container.getValue().isPresent()) {
//                    try {
//                        container.getNotProducedYet().await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Integer value = container.getValue().get();
//                container.setValue(Optional.empty());
//                System.out.println("Consuming " + value);
//            } finally {
//                relock.unlock();
//            }
//        }
//
//
}


