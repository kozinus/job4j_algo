package ru.job4j.algo.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TreeUtils<T> {

    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int nodes = 0;
        Queue<Node<T>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        Node<T> node;
        while (queue.peek() != null) {
            nodes++;
            node = queue.poll();
            queue.addAll(node.getChildren());
        }

        return nodes;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        List<T> result = new LinkedList<>();
        Queue<Node<T>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        Node<T> node;
        while (queue.peek() != null) {
            node = queue.poll();
            result.add(node.getValue());
            queue.addAll(node.getChildren());
        }
       return result;
    }
}