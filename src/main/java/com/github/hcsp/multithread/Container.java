package com.github.hcsp.multithread;

import java.util.Optional;

public class Container {
    private Optional<Integer> container = Optional.empty();

    public int take() {
        int value = container.get();
        container = Optional.empty();
        return value;
    }

    public void put(int value) {
        container = Optional.of(value);
    }

    public boolean isPresent() {
        return container.isPresent();
    }
}
