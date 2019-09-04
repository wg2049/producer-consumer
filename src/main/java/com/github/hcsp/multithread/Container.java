package com.github.hcsp.multithread;

import java.util.Optional;

public class Container {
    Optional<Integer> container = Optional.empty();

    public Optional<Integer> getContainer() {
        return container;
    }

    public void setContainer(Optional<Integer> container) {
        this.container = container;
    }
}
