package ru.job4j.algo.hash;

import java.util.*;

public class Brackets {
    public boolean isValid(String s) {
        char[] text = s.toCharArray();
        boolean result = true;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> brackets =
                new HashMap<>(Map.of(')', '(', ']', '[', '}', '{'));
        for (char symbol : text) {
            if (brackets.containsValue(symbol)) {
                stack.add(symbol);
            } else if (brackets.containsKey(symbol)) {
                if (stack.empty() || !Objects.equals(stack.pop(), brackets.get(symbol))) {
                    result = false;
                    break;
                }
            }

        }
        return result && stack.empty();
    }
}