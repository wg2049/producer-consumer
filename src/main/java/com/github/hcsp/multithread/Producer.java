package com.github.hcsp.multithread;

public class Producer extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Producing " + i);
            produce(i);
        }
    }

    // 生产一个整数i，供消费者使用
    private void produce(int i) {}
}
