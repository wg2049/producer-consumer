package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//这样就把Container暴露出去
public class Container {

    private Condition notConsumdYet; //还没有被消费
    private Condition notProducedYet; //还没有被生产

    //任何事件都是和锁绑在一起的，所以需要创建一个锁
    public Container(ReentrantLock lock) {
        this.notConsumdYet = lock.newCondition();
        this.notProducedYet = lock.newCondition();
    }

    //用get()外面可以读取到这两个条件
    public Condition getNotConsumdYet() {
        return notConsumdYet;
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }

    private Optional<Integer> value = Optional.empty();

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
    }
}
