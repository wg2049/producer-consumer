package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container2 {

    private Condition notConsumedYet; // 尚未被消费掉
    private Condition notProducedYet; // 尚未被生产出来
    private Optional<Integer> value = Optional.empty();

    public Container2(ReentrantLock lock) {
        this.notConsumedYet = lock.newCondition();
        this.notProducedYet = lock.newCondition();
    }

    public Condition getNotConsumedYet() {
        return notConsumedYet;
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
    }
}
