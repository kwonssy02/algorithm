// https://programmers.co.kr/learn/courses/30/lessons/70130
package Programmers.Level3.스타수열;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] a) {
        int[][] arr = new int[a.length][2]; // 숫자개수, 이전index
        for (int i = 0; i < a.length; i++) {
            arr[i][1] = -100;
        }

        // 1, 1, 1, 1 과 같이 같은 숫자가 연속 시, 맨앞/맨뒤만 카운트
        for (int i = 0; i < a.length; i++) {
            if (arr[a[i]][1] == i - 1) {
                arr[a[i]][1] = i;
                if (i + 1 < a.length && a[i] != a[i + 1]) arr[a[i]][0]++;
                continue;
            }
            arr[a[i]][1] = i;
            arr[a[i]][0]++;
        }

        int max = 0;
        List<Integer> mainNumbers = new ArrayList<>(); // 등장 횟수가 가장 큰 숫자의 리스트
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i][0]) {
                max = arr[i][0];
                mainNumbers = new ArrayList<>();
                mainNumbers.add(i);
            } else if (max == arr[i][0]) {
                mainNumbers.add(i);
            }
        }

//        System.out.println("mainNum = " + mainNum);
        // mainNumber 로 시뮬레이션
        int maxLength = 0;
        for (Integer mainNumber : mainNumbers) {
            int length = 0;
            int first = -1;
            for (int i = 0; i < a.length; i++) {
                if (first == -1) {
                    first = a[i];
                } else {
                    if (a[i] == first) continue;
                    if (first != mainNumber && a[i] != mainNumber) continue;
                    first = -1;
                    length += 2;
                }
            }
            if (maxLength < length)
                maxLength = length;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{0}));
        System.out.println(new Solution().solution(new int[]{5, 2, 3, 3, 5, 3}));
        System.out.println(new Solution().solution(new int[]{0, 3, 3, 0, 7, 2, 0, 2, 2, 0}));
        System.out.println(new Solution().solution(new int[]{1, 2, 1, 1, 1, 1, 1, 1}));
        System.out.println(new Solution().solution(new int[]{2, 1, 1, 1, 1, 2, 3, 2, 4}));
        System.out.println(new Solution().solution(new int[]{1, 2, 1, 2, 1, 2}));
    }
}