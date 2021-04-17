// https://programmers.co.kr/learn/courses/30/lessons/12951
package Programmers.Level2.JadenCase문자열만들기;

class Solution {
    public static String solution(String s) {
        char[] arr = new char[s.length()];
        boolean isFirstChar = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isFirstChar && ('a' <= c && c <= 'z')) {
                arr[i] = (char) (c - 32);
            } else if (!isFirstChar && ('A' <= c && c <= 'Z')) {
                arr[i] = (char) (c + 32);
            } else {
                arr[i] = c;
            }

            isFirstChar = c == ' ';
        }

        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(solution("3people unFollowed me"));
        System.out.println(solution("for the last week"));
    }
}