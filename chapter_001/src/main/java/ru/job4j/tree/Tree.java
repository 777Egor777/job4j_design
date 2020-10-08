package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E rootValue) {
        root = new Node<>(rootValue);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentOpt = findBy(parent);
        if (parentOpt.isPresent() && findBy(child).isEmpty()) {
            rsl = true;
            Node<E> parentNode = parentOpt.get();
            parentNode.children.add(new Node<>(child));
        }
        return rsl;
    }

    private Optional<Node<E>> bfs(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (predicate.test(node)) {
                rsl = Optional.of(node);
                break;
            }
            queue.addAll(node.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return bfs(node -> node.value.equals(value));
    }

    public boolean isBinary() {
        Optional<Node<E>> opt = bfs(node -> node.children.size() > 2);
        return opt.isEmpty();
    }
}
