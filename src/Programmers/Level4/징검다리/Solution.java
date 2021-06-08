// https://programmers.co.kr/learn/courses/30/lessons/43236
package Programmers.Level4.징검다리;

import org.assertj.core.api.Assertions;

import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        // 최소 간격을 가진 바위부터 제거..
        Arrays.sort(rocks);
        for (int rock : rocks) {
            System.out.print(rock + " ");
        }
        System.out.println();

        int[] gaps = new int[rocks.length + 1];
        gaps[0] = rocks[0];
        for (int i = 1; i < rocks.length; i++) {
            gaps[i] = rocks[i] - rocks[i - 1];
        }
        gaps[gaps.length - 1] = distance - rocks[rocks.length - 1];

        for (int i = 0; i < gaps.length; i++) {
            System.out.print(gaps[i] + " ");
        }
        System.out.println();

        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        Assertions.assertThat(solution.solution(25, new int[]{2, 14, 11, 21, 17}, 2)).isEqualTo(4);
    }
}
// 11 14 17 21
// 11  6  4