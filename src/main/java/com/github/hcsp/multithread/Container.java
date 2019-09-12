package com.github.hcsp.multithread;

import java.util.Optional;

class Container {
    public Optional<Integer> value = Optional.empty();

    Optional<Integer> getValue() {
        return value;
    }

    void setValue(Optional<Integer> value) {
        this.value = value;
    }
}
