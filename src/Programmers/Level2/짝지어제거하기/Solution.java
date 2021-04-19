// https://programmers.co.kr/learn/courses/30/lessons/12973
package Programmers.Level2.짝지어제거하기;

import java.util.Stack;

class Solution {
    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                stack.add(c);
            }
        }

        if (!stack.isEmpty())
            return 0;

        return 1;
    }

    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
        System.out.println(solution("cdcd"));
        System.out.println(solution("abcdeeffdcba"));
    }
}

/*
    abcdeeffdcba -> 1 -> Stack 가능? 100만개인데?
 */