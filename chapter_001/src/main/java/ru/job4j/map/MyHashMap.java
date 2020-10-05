package ru.job4j.map;

public class MyHashMap<K, V> {
    private final int length = (int) 1e6 + 113;
    private int size = 0;
    private final Node[] storage = new Node[length];

    private int position(K key) {
        return hash(key) & (length - 1);
    }

    private int hash(K key) {
        int h = key.hashCode();
        return key == null ? 0 : h ^ (h >>> 16);
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int pos = position(key);
        if (storage[pos] == null) {
            result = true;
            storage[pos] = new Node(key, value);
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
