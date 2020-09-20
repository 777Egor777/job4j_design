package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
        in.push(value);
    }

    public T poll() {
        T result = out.pop();
        if (result == null) {
            T val = in.pop();
            if (val == null) {
                throw new NoSuchElementException();
            } else {
                out.push(val);
            }
            while ((val = in.pop()) != null) {
                out.push(val);
            }
            result = out.pop();
        }
        return result;
    }
}