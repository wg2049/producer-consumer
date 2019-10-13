package com.github.hcsp.multithread;

import java.util.Optional;

public class Consumer extends Thread {
    private Object lock;
    private Container container;

    public Consumer(Container container, Object lock) {
        this.container = container;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                // 把wait()调用放在循环中，是为了防止中断和假的唤醒
                // 保证条件不满足时一直处于waiting状态
                while (!container.getValue().isPresent()) {
                    try {
                        // 线程进入waiting状态，不再继续向下执行，等待其他线程的唤醒
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer v = container.getValue().get();
                container.setValue(Optional.empty());
                System.out.println("Consuming " + v);
                lock.notify();
            }
        }
    }
}
