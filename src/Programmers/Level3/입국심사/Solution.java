// https://programmers.co.kr/learn/courses/30/lessons/43238
package Programmers.Level3.입국심사;

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long time = times[0] * n; // 최대로 걸리는 시간이므로, 심사시간이 더 긴 심사관은 계산에서 제외
        while (true) {
            for (int i = 1; i < times.length; i++) {
                if ((n-1)*times[0] >= times[i]) {
                    n--;
                    time -= times[0];
                } else
                    break;
            }
            if(n == 0)
                break;
        }

        return time;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(6, new int[]{7, 10}));
        System.out.println(solution.solution(10, new int[]{1, 2, 6})); // 1*6, 2*3, 6*1
        System.out.println(solution.solution(10, new int[]{1, 2, 5})); // 1*6, 2*3, 5*1
    }
}