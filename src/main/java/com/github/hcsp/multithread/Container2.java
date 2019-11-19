package com.github.hcsp.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container2 extends Container{
    private ReentrantLock lock;
    private Condition notProducedYet;
    private Condition notConsumedYet;

    public Container2(ReentrantLock lock) {
        this.lock = lock;
        this.notProducedYet = lock.newCondition();
        this.notConsumedYet = lock.newCondition();
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }

    public Condition getNotConsumedYet() {
        return notConsumedYet;
    }
}
