// https://programmers.co.kr/learn/courses/30/lessons/68935
package Programmers.Level1._3진법뒤집기;

import java.util.Stack;

class Solution {
    public static int solution(int n) {
        Stack<Integer> stack = new Stack<>();

        while (n > 0) {
            stack.push(n % 3);
            n /= 3;
        }

        int answer = 0;
        int m = 1;
        while (!stack.isEmpty()) {
            answer += stack.pop() * m;
            m *= 3;
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution(100000000);
//        solution(45);
    }
}
