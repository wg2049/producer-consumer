package com.github.hcsp.multithread;

import java.util.Optional;

//这样就把Container暴露出去
public class Container {
    private Optional<Integer> value = Optional.empty();

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Optional<Integer> value) {
        this.value = value;
    }
}
