package com.github.hcsp.multithread;

import java.util.Random;

public class Producer extends Thread {
    private Object lock;
    private Food food;

    public Producer(Food food, Object lock) {
        this.food = food;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (food.getFood() != null) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                food.setFood(new Random().nextInt());
                System.out.println("Producing " + food.getFood());
                lock.notify();

            }
        }
    }
}
