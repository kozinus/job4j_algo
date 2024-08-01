package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

class IntervalMerger {

    public int[][] merge(int[][] intervals) {
        int[][] result = new int[][] {};
        int pointer = 0;
        if (intervals.length > 0) {
            Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
            result = new int[intervals.length][2];
            result[0] = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (result[pointer][1] >= intervals[i][0]) {
                    result[pointer][1] = intervals[i][1];
                } else {
                    pointer++;
                    result[pointer] = intervals[i];
                }
            }
        }
        return Arrays.copyOf(result, pointer + 1);
    }
}