package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
  ReentrantLock lock;
  Container container;

  public Producer(ReentrantLock lock, Container container) {
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
          container.getNotProducedYet().await();
        }
        Integer random = new Random().nextInt();
        System.out.println("Producing " + random);
        container.setOptional(Optional.of(random));
        container.getNotConsumedYet().signal();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
