package ru.job4j.collection.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        boolean result;
        Node prev = node;
        if (node.key.equals(key)) {
            result = false;
        } else if (node.key.compareTo(key) > 0) {
            node = node.left;
            if (node == null) {
                prev.left = new Node(key);
                result = true;
            } else {
                result = put(node, key);
            }
        } else {
            node = node.right;
            if (node == null) {
                prev.right = new Node(key);
                result = true;
            } else {
                result = put(node, key);
            }
        }
        return result;
    }

    public boolean contains(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            result = false;
        } else {
            result = root.key.equals(key) || find(root, key) != null;
        }
        return result;
    }

    private Node find(Node node, T key) {
        Node result;
        if (node.key.equals(key)) {
            result = node;
        } else {
            node = node.key.compareTo(key) > 0 ? node.left : node.right;
            if (node == null) {
                result = null;
            } else {
                result = find(node, key);
            }
        }
        return result;
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.left, list);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            inSymmetricalOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node result;
        if (node.left == null) {
            result = node;
        } else {
            result = minimum(node.left);
        }
        return result;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node result;
        if (node.right == null) {
            result = node;
        } else {
            result = maximum(node.right);
        }
        return result;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private final T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}