package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
  ReentrantLock lock;
  Container container;

  public Consumer(ReentrantLock lock, Container container) {
    this.lock = lock;
    this.container = container;
  }

  @Override
  public void run() {
    try {
      lock.lock();
      for (int i = 0; i < 10; i++) {
        while (container.getOptional().isPresent()) {
          // 当前容器有值
          System.out.println("Consuming " + container.getOptional().get());
          container.setOptional(Optional.empty());
          container.getNotProducedYet().signal();
        }
        container.getNotConsumedYet().await();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
