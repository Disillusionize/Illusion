package org.example.illusion.api;

import java.util.Arrays;
import java.util.List;

public class Manager<T extends Feature> {
    protected List<T> elements;

    @SafeVarargs
    public Manager(T... elements) {
        this.elements = Arrays.asList(elements);
    }

    public T getElement(String name) {
        return elements.stream()
                .filter(element -> element.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<T> getElements() {
        return elements;
    }
}