package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final int maxSize;
    private int currentSize = 0;
    private Object[] array;

    public SimpleArray(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
    }

    public void add(T model) {
        if (currentSize >= maxSize) {
            throw new IndexOutOfBoundsException();
        }
        array[currentSize++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, currentSize);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, currentSize);
        if (index < currentSize - 1) {
            System.arraycopy(array, index + 1, array, index, currentSize - 1 - index);
        }
        array[currentSize - 1] = null;
        currentSize--;
    }

    public T get(int index) {
        Objects.checkIndex(index, currentSize);
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < currentSize;
            }

            @Override
            public T next() {
                T result = get(position);
                position++;
                return result;
            }
        };
    }
}
