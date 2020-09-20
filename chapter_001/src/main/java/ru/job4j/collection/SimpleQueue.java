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
        T result;
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (out.size() > 0) {
            result = out.pop();
        } else {
            while (in.size() > 0) {
                out.push(in.pop());
            }
            result = out.pop();
        }
        return result;
    }
}