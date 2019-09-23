package com.github.hcsp.multithread;

import java.util.Optional;

public class Container {
    private Optional container;

    public Container(Optional container) {
        this.container = container;
    }

    public Optional getContainer() {
        return container;
    }

    public void setContainer(Optional container) {
        this.container = container;
    }
}
