package ru.job4j.algo.tree;


import java.util.*;
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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            if (node.getValue().equals(parent)) {
                node.addChild(new Node<>(child));
                result = true;
                break;
            }
            stack.addAll(node.getChildren());
        }
        return result;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Node<T> result = null;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            if (node.getValue().equals(key)) {
                result = node;
                break;
            }
            stack.addAll(node.getChildren());
        }
        return Optional.ofNullable(result);
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Node<T> result = null;
        if (root.getValue().equals(key)) {
            result = new Node<>(key);
            result.addAllChildren(root.getChildren());
        }

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty() && result == null) {
            Node<T> node = stack.pop();
            for (Node<T> child : node.getChildren()) {
                if (child.getValue().equals(key)) {
                    result = new Node<>(key);
                    result.addAllChildren(child.getChildren());
                    node.removeChild(child);
                    break;
                }
                stack.add(child);
            }
        }
        return Optional.ofNullable(result);
    }
}