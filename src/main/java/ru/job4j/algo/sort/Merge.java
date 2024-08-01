package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftPointer = 0;
        int rightPointer = 0;
        while (rightPointer < right.length && leftPointer < left.length) {
            if (left[leftPointer] < right[rightPointer]) {
                result[leftPointer + rightPointer] = left[leftPointer];
                leftPointer++;
            } else {
                result[leftPointer + rightPointer] = right[rightPointer];
                rightPointer++;
            }
        }
        int resultPointer = leftPointer + rightPointer;
        while (rightPointer < right.length) {
            result[resultPointer] = right[rightPointer];
            resultPointer++;
            rightPointer++;
        }
        while (leftPointer < left.length) {
            result[resultPointer] = left[leftPointer];
            resultPointer++;
            leftPointer++;
        }
        return result;
    }
}