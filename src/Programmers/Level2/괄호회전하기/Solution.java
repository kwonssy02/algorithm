// https://programmers.co.kr/learn/courses/30/lessons/76502
package Programmers.Level2.괄호회전하기;

import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution("}]()[{");
    }

    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            final char[] chars = (s.substring(i) + s.substring(0, i)).toCharArray();
            if (chars[0] == ')' || chars[0] == ']' || chars[0] == '}')
                continue;

            Stack<Character> stack = new Stack<>();
            boolean isRight = true;
            for (char c : chars) {
                if (c == '(' || c == '[' || c == '{')
                    stack.add(c);
                else if (!stack.isEmpty() && c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (!stack.isEmpty() && c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (!stack.isEmpty() && c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    isRight = false;
                    break;
                }
            }
            if (isRight && stack.isEmpty()) {
                answer++;
            }
        }

        return answer;
    }
}