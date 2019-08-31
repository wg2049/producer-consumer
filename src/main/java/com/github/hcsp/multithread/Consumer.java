package com.github.hcsp.multithread;

public class Consumer extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Consuming " + consume());
        }
    }

    // 消费一个生产者生产出来的整数，将其返回
    private int consume() {
        return 0;
    }
}
