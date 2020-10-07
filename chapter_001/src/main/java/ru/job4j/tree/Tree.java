package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E rootValue) {
        root = new Node<>(rootValue);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentOpt = findBy(parent);
        Optional<Node<E>> childOpt = findBy(child);
        if (parentOpt.isPresent() && childOpt.isEmpty()) {
            rsl = true;
            Node<E> parentNode = parentOpt.get();
            parentNode.children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.value.equals(value)) {
                rsl = Optional.of(node);
                break;
            }
            queue.addAll(node.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.children.size() > 2) {
                rsl = false;
                break;
            }
            queue.addAll(node.children);
        }
        return rsl;
    }
}
