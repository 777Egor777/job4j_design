package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E e) {
        if (!array.contains(e)) {
            array.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Iterator<E> arrayIt = array.iterator();
            @Override
            public boolean hasNext() {
                return arrayIt.hasNext();
            }

            @Override
            public E next() {
                return arrayIt.next();
            }
        };
    }
}
