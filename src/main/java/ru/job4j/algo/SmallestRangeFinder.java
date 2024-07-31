package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = new int[2];
        int diff = 0x7FFFFFFF;
        int streak = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                streak++;
                if (streak >= k && diff > (nums[i] - nums[i - k + 1])) {
                    diff = nums[i] - nums[i - k + 1];
                    result[0] = i - k + 1;
                    result[1] = i;
                }
                if (diff == k) {
                    break;
                }
            } else {
                streak = 1;
            }
        }
        return Arrays.equals(result, new int[]{0, 0}) ? null : result;
    }

    public static void main(String[] args) {
        int[] nums =
                {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}