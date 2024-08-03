package ru.job4j.algo.tree;

import java.util.*;

public class Node<E> {
     private E value;
     private List<Node<E>> children = new ArrayList<>();

    public Node(E value) {
        this.value = value;
    }

    @SafeVarargs
    public Node(E value, Node<E>... children) {
        this.value = value;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public String toString() {
        return String.format("Node{ %s }", value);
    }

    public E getValue() {
        return value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<E>> children) {
        this.children = children;
    }

    public void addChild(Node<E> children) {
        this.children.add(children);
    }

    public void addAllChildren(Collection<Node<E>> children) {
        this.children.addAll(children);
    }

    public Optional<Node<E>> removeChild(int i) {
        return Optional.ofNullable(this.children.remove(i));
    }

    public void removeChild(Node<E> node) {
        this.children.remove(node);
    }
}