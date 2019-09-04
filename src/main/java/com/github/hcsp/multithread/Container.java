package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Container {
    private Condition notConsumedYet;
    private Condition notProducedYet;
    Optional<Integer> container = Optional.empty();

    public Container(Lock lock) {
        this.notConsumedYet = lock.newCondition();
        this.notProducedYet = lock.newCondition();
    }

    public Condition getNotConsumedYet() {
        return notConsumedYet;
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }

    public Optional<Integer> getContainer() {
        return container;
    }

    public void setContainer(Optional<Integer> container) {
        this.container = container;
    }
}
