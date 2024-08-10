package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void putAndPreOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPreOrder();
        assertThat(list).containsExactly(4, 2, 1, 3, 6, 5, 7);
        for (int i = 8; i < 17; i++) {
            tree.insert(i);
        }
        list = tree.inPreOrder();
        assertThat(list).containsExactly(8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15, 16);
    }

    @Test
    void putAndSimmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 17; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPreOrder();
        assertThat(list).containsExactly(8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15, 16);
        tree.remove(8);
        list = tree.inPreOrder();
        assertThat(list).containsExactly(9, 4, 2, 1, 3, 6, 5, 7, 12, 10, 11, 14, 13, 15, 16);
    }
}