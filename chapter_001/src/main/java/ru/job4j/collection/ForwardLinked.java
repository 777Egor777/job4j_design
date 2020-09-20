package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T value) {
        size++;
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void addFirst(T value) {
        size++;
        Node<T> node = new Node<>(value, head);
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Node<T> curHead = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        curHead.next = null;
        return curHead.value;
    }

    public T deleteLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        T result;
        if (head.next == null) {
            result = head.value;
            head = null;
            tail = null;
        } else {
            Node<T> node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            result = node.next.value;
            node.next = null;
            tail = node;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}