// https://programmers.co.kr/learn/courses/30/lessons/42883
package Programmers.Level2.큰수만들기;

import java.util.Stack;

class Solution {

    public static String solution(String number, int k) {
        char[] arr = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }

        // for 문 최대값을 stack size로 설정 시, k 개수만큼 빼지않은 length가 들어갈 수 있음.
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.get(i);
        }

        return new String(arr);
    }

    /*
    public static String solution(String number, int k) {
        // 0번째부터 k까지 max찾고, 거기까지 substring
        // 1번째부터 k'까지 max찾고, 거기까지 substring
        // ...

        int index = 0;
        while(k > 0) {
            if(index+k == number.length()) {
                number = number.substring(0, index);
                break;
            }

            char maxChar = number.charAt(index);
            int maxCharIndex = index;
            for (int i = index; i <= index+k; i++) {
                if(maxChar < number.charAt(i)) {
                    maxChar = number.charAt(i);
                    maxCharIndex = i;
                }
            }
            k -= maxCharIndex - index;
            number = number.substring(0, index) + number.substring(maxCharIndex);
            index++;
        }

        return number;
    }
     */

    public static void main(String[] args) {
        System.out.println(solution("11", 1));
        System.out.println(solution("12", 1));
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
    }
}