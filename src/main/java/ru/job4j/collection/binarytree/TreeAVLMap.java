package ru.job4j.collection.binarytree;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean insert(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key)) {
            root = insert(root, key, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            if (node.key.equals(key)) {
                node.value = value;
                result = node;
            } else {
                int comparisonResult = key.compareTo(node.key);
                if (comparisonResult < 0) {
                    node.left = insert(node.left, key, value);
                } else {
                    node.right = insert(node.right, key, value);
                }
                updateHeight(node);
                result = balance(node);
            }
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && Objects.nonNull(get(key))) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public V get(T key) {
        V result;
        if (Objects.isNull(root)) {
            result = null;
        } else {
            result = get(root, key);
        }
        return result;
    }

    private V get(Node node, T key) {
        V result;
        if (node.key.equals(key)) {
            result = node.value;
        } else {
            node = node.key.compareTo(key) > 0 ? node.left : node.right;
            if (node == null) {
                result = null;
            } else {
                result = get(node, key);
            }
        }
        return result;
    }

    public Set<T> keySet() {
        Set<T> result = new HashSet<>();
        Node node = root;
        return keySet(node, result);
    }

    private Set<T> keySet(Node localRoot, Set<T> set) {
        if (localRoot != null) {
            set.add(localRoot.key);
            keySet(localRoot.left, set);
            keySet(localRoot.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        Node node = root;
        return values(node, result);
    }

    private List<V> values(Node localRoot, List<V> list) {
        if (localRoot != null) {
            values(localRoot.left, list);
            list.add(localRoot.value);
            values(localRoot.right, list);
        }
        return list;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
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

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}