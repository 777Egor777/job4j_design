package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> curHead = head;
        head = head.next;
        curHead.next = null;
    }

    public T deleteLast() {
        T result;
        if (head == null) {
            throw new NoSuchElementException();
        } else
        if (head.next == null) {
            result = head.value;
            head = null;
        } else {
            Node<T> node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            result = node.next.value;
            node.next = null;
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