package ru.job4j.collection;

public class Node<E> {
    E model;
    Node<E> prev;
    Node<E> next;

    public Node(Node<E> prev, E model, Node<E> next) {
        this.prev = prev;
        this.model = model;
        this.next = next;
    }
}
