package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedArray<T> implements Iterable<T> {
    private Node<T> first = null;
    private Node<T> last = null;
    private int modCount = 0;
    private int size = 0;

    public void add(T model) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<T>(l, model, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = first;
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }
        return node.model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int itModCount = modCount;
            private Node<T> node = first;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T result = node.model;
                node = node.next;
                return result;
            }
        };
    }
}
