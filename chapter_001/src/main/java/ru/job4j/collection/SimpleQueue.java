package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public int size() {
        return in.size() + out.size();
    }

    public void push(T value) {
        in.push(value);
    }

    public T poll() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }
}