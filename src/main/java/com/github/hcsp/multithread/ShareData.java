package com.github.hcsp.multithread;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;

public class ShareData {
    //方法二:定义2个变量，分别表示还没生产出来、还没有被消费掉的变量
    private Condition notConsumedYet;
    private Condition notProducedYet;

//    public ShareData(ReentrantLock lock) {
//        this.notConsumedYet = lock.newCondition();
//        this.notProducedYet = lock.newCondition();
//    }

    //方法一：定义一个Option<Integer> value 变量
    private Optional<Integer> value = Optional.empty();

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
    }

    public Condition getNotConsumedYet() {
        return notConsumedYet;
    }

    public Condition getNotProducedYet() {
        return notProducedYet;
    }
}
