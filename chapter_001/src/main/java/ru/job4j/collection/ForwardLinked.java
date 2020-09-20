package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    public int getSize() {
        return size;
    }

    public void add(T value) {
        size++;
        modCount++;
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
    }

    public void addFirst(T value) {
        size++;
        modCount++;
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        modCount++;
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Node<T> curHead = head;
        head = head.next;
        curHead.next = null;
        return curHead.value;
    }

    private Node<T> rec(Node<T> node, Node<T> nextNode) {
        Node<T> result;
        if (nextNode.next == null) {
            result = nextNode;
        } else {
            result = rec(nextNode, nextNode.next);
        }
        nextNode.next = node;
        node.next = null;
        return result;
    }

    public void revert() {
        modCount++;
        if (size > 1) {
            head = rec(head, head.next);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            private int itModCount = modCount;

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