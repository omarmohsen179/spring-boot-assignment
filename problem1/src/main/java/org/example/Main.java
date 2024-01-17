package org.example;

import java.util.Stack;

public class Main {
    public static String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            } else if (arr[i] == ')') {
                reverseSubstring(arr, stack.pop() + 1, i - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != '(' && c != ')') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static void reverseSubstring(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String[] testCases = {
                "abd(jnb)asdf",
                "abdjnbasdf",
                "dd(df)a(ghhh)"
        };

        for (String testCase : testCases) {
            String result = reverseParentheses(testCase);
            System.out.println("Input: " + testCase);
            System.out.println("Output: " + result);
            System.out.println();
        }
    }
}