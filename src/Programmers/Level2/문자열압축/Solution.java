// https://programmers.co.kr/learn/courses/30/lessons/60057
package Programmers.Level2.문자열압축;

//TODO: 재귀 풀이 보기
class Solution {
    public static int solution(String s) {
        int p = s.length() / 2 + 1; // 문자열 압축 단위
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= p; i++) {
            String result = "";
            int count = 0;
            String temp = null;
            for (int j = 0; j < s.length(); j += i) {
                String substring = s.substring(j, (j + i >= s.length() ? s.length() : j + i));

                if (temp == null) {
                    temp = substring;
                    count = 1;
                } else if (temp.equals(substring)) {
                    count++;
                } else {
                    result += (count == 1 ? "" : count) + temp;
                    temp = substring;
                    count = 1;
                }
            }
            result += (count == 1 ? "" : count) + temp;

            if (min > result.length())
                min = result.length();

//            System.out.println(result);
        }

        return min;
    }

    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }
}