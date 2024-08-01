package ru.job4j.algo.hash;

import java.util.HashSet;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String str) {
        String result = "";
        HashSet<Character> set = new HashSet<>();
        String rightSubstring;
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            if (!set.contains(symbol)) {
                set.add(symbol);
                continue;
            }
            result = str.substring(0, i);
            if (result.length() < ((str.length() + 1) / 2)) {
                rightSubstring = longestUniqueSubstring(str.substring(i));
                result = result.length() > rightSubstring.length()
                        ? result : rightSubstring;
            }
            break;
        }
        if (result.isEmpty()) {
            result = str;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("password"));
    }
}