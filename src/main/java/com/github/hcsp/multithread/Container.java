package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container {
    private Condition notConsumedYet;
    private Condition notProducedYet;
    Optional<Integer> value  = Optional.empty();

    public Container(ReentrantLock lock) {
        this.notConsumedYet = lock.newCondition();
        this.notProducedYet = lock.newCondition();
    } //把两个条件变成锁

    public Condition getNotConsumedYet() {
        return notConsumedYet;
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }
    //读取锁要用get方法？

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
    }
}
