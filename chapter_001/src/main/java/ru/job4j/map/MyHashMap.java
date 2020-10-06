package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Iterable<Node> {
    private int length = 10;
    private int size = 0;
    private double load = 0.0;
    private final double loadFactor = 0.75;
    private int modCount = 0;
    private Node[] storage = new Node[length];

    private int position(K key) {
        return hash(key) & (length - 1);
    }

    private int hash(K key) {
        int h = key.hashCode();
        return key == null ? 0 : h ^ (h >>> 16);
    }

    private void expand() {
        modCount++;
        length *= 1.5;
        Node[] newStorage = new Node[length];
        for (int i = 0; i < storage.length; ++i) {
            if (storage[i] != null) {
                K key = (K) storage[i].key;
                V value = (V) storage[i].value;
                int pos = position(key);
                if (newStorage[pos] == null) {
                    newStorage[pos] = new Node(key, value);
                }
            }
        }
        storage = newStorage;
    }

    public boolean insert(K key, V value) {
        modCount++;
        boolean result = false;
        int pos = position(key);
        if (storage[pos] == null) {
            result = true;
            storage[pos] = new Node(key, value);
        }
        size++;
        load = (double) size / length;
        if (load >= loadFactor) {
            expand();
        }
        return result;
    }

    public V get(K key) {
        modCount++;
        Node node = storage[position(key)];
        return node == null ? null : (V) node.value;
    }

    public boolean delete(K key) {
        modCount++;
        boolean result = false;
        int pos = position(key);
        if (storage[pos] != null) {
            result = true;
            storage[pos] = null;
        }
        return result;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<>() {
            private int position = 0;
            private int itModCount = modCount;

            @Override
            public boolean hasNext() {
                while (position < length && storage[position] == null) {
                    position++;
                }
                return position < length;
            }

            @Override
            public Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return storage[position];
            }
        };
    }
}