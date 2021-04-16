// https://programmers.co.kr/learn/courses/30/lessons/12909
package Programmers.Level2.올바른괄호;

import java.util.Stack;

class Solution {
    static boolean solution(String s) {
        if (s.charAt(0) == ')')
            return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() == ')') {
                    return false;
                }
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(solution("("));
        System.out.println(solution(")"));
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
        System.out.println(solution("()))))))"));
    }
}