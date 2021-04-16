// https://programmers.co.kr/learn/courses/30/lessons/12939
package Programmers.Level2.최댓값과최솟값;

import java.util.Arrays;

class Solution {
    public static String solution(String s) {
        final int[] arr = Arrays.stream(s.split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();

        return arr[0] + " " + arr[arr.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(solution("1 2 3 4"));
        System.out.println(solution("-1 -2 -3 -4"));
        System.out.println(solution("-1 -1"));
    }
}