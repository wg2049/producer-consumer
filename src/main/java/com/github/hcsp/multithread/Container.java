package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container {
    private Optional<Integer> value = Optional.empty();
    private Condition notProducedYet;
    private Condition notConsumedYet;

    public Container(ReentrantLock lock) {
        notConsumedYet = lock.newCondition();
        notProducedYet = lock.newCondition();
    }

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
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
}

