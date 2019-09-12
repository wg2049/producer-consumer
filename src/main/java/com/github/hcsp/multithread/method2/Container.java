package com.github.hcsp.multithread.method2;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container {
    private Condition notBeenProduct;
    private Condition notBeenConsumer;
    private Optional<Integer> container = Optional.empty();

    public Container(ReentrantLock lock) {
        this.notBeenProduct = lock.newCondition();
        this.notBeenConsumer = lock.newCondition();
    }

    public Condition getNotBeenProduct() {
        return notBeenProduct;
    }

    public Condition getNotBeenConsumer() {
        return notBeenConsumer;
    }

    public void setContainer(Optional<Integer> container) {
        this.container = container;
    }

    public Optional<Integer> getContainer() {
        return container;
    }
}
