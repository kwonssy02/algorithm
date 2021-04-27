// https://programmers.co.kr/learn/courses/30/lessons/43238
package Programmers.Level3.입국심사;

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long left = 0;
        long right = (long)times[0] * n; // 최대로 걸리는 시간
        long mid;
        while (left <= right) {
            mid = (left + right) / 2;
            long temp = n;
            boolean multiply = false;
            for (int i = 0; i < times.length; i++) {
                if (times[i] > mid)
                    break;
                if (mid % times[i] == 0) multiply = true;
                temp -= mid / times[i];
            }
            if (temp > 0) { // 시간이 더 필요
                left = mid + 1;
            } else if (temp < 0 || (temp == 0 && !multiply)) { // 더 빨리 가능
                right = mid - 1;
            } else if (temp == 0 && multiply) {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(6, new int[]{7, 10}));
        System.out.println(solution.solution(10, new int[]{1, 2, 6})); // 1*6, 2*3, 6*1
        System.out.println(solution.solution(10, new int[]{1, 2, 5})); // 1*6, 2*3, 5*1
        System.out.println(solution.solution(50, new int[]{1, 1, 1, 1, 1})); // 1*6, 2*3, 5*1
        System.out.println(solution.solution(1000000000, new int[]{1000000000,1000000000,1000000000})); // 1*6, 2*3, 5*1
    }
}