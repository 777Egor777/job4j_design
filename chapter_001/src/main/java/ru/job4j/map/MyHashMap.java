package ru.job4j.map;

public class MyHashMap<K, V> {
    private int length = 10;
    private int size = 0;
    private double load = 0.0;
    private final double loadFactor = 0.75;
    private Node[] storage = new Node[length];

    private int position(K key) {
        return hash(key) & (length - 1);
    }

    private int hash(K key) {
        int h = key.hashCode();
        return key == null ? 0 : h ^ (h >>> 16);
    }

    private void expand() {
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
        Node node = storage[position(key)];
        return node == null ? null : (V) node.value;
    }

    public boolean delete(K key) {
        boolean result = false;
        int pos = position(key);
        if (storage[pos] != null) {
            result = true;
            storage[pos] = null;
        }
        return result;
    }
}
