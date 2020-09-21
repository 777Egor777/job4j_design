package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private int modCount = 0;
    private int size = 0;
    private Object[] container = new Object[10];

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    private Object[] grow(Object[] array) {
        int newCapacity = (int) (array.length * 1.5);
        return Arrays.copyOf(array, newCapacity);
    }

    public void add(T model) {
        if (size == container.length) {
            container = grow(container);
        }
        container[size++] = model;
        modCount++;
    }

    public boolean contains(T model) {
        boolean result = false;
        for (int index = 0; index < size; ++index) {
            if (Objects.equals(get(index), model)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int itModCount = modCount;
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T result = get(position);
                position++;
                return result;
            }
        };
    }
}
