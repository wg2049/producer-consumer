package com.github.hcsp.multithread;

import java.util.Optional;

public class Container {
    Optional<Integer> container;

    public Optional<Integer> getContainer() {
        return container;
    }

    public void setContainer(Optional<Integer> container) {
        this.container = container;
    }

    public Container(Optional<Integer> container) {
        this.container = container;
    }
}
