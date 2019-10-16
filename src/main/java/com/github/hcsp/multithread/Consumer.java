package com.github.hcsp.multithread;

public class Consumer extends Thread {
    private Food food;
    private Object lock;


    public Consumer(Food food, Object lock) {
        this.food = food;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (food.getFood() == null) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming " + food.getFood());
                food.setFood(null);
                lock.notify();
            }
        }
    }
}
