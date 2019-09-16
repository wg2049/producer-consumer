package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container {
  Optional<Integer> optional;
  Condition notProducedYet;
  Condition notConsumedYet;

  public Optional<Integer> getOptional() {
    return optional;
  }

  public void setOptional(Optional<Integer> optional) {
    this.optional = optional;
  }

  public Condition getNotProducedYet() {
    return notProducedYet;
  }

  public void setNotProducedYet(Condition notProducedYet) {
    this.notProducedYet = notProducedYet;
  }

  public Condition getNotConsumedYet() {
    return notConsumedYet;
  }

  public void setNotConsumedYet(Condition notConsumedYet) {
    this.notConsumedYet = notConsumedYet;
  }

  public Container(Optional<Integer> optional, ReentrantLock lock) {
    this.optional = optional;
    this.notProducedYet = lock.newCondition();
    this.notConsumedYet = lock.newCondition();
  }
}
