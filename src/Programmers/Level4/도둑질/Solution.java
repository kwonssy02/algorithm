// https://programmers.co.kr/learn/courses/30/lessons/42897
package Programmers.Level4.도둑질;

import org.assertj.core.api.Assertions;

class Solution {
    public int solution(int[] money) {
        // dp: // i번째를 털거나(j=1), 안털거나(j=0)
        int[][] dp1 = new int[money.length][2]; // 0번째를 털었을 때
        int[][] dp2 = new int[money.length][2]; // 0번째를 털지 않았을 때

        dp1[1][0] = money[0];
        dp1[1][1] = 0;
        for (int i = 2; i < money.length - 1; i++) {
            dp1[i][0] = Math.max(dp1[i - 1][1], dp1[i - 1][0]);
            dp1[i][1] = dp1[i - 1][0] + money[i];
        }

        dp2[1][0] = 0;
        dp2[1][1] = money[1];
        for (int i = 2; i < money.length; i++) {
            dp2[i][0] = Math.max(dp2[i - 1][1], dp2[i - 1][0]);
            dp2[i][1] = dp2[i - 1][0] + money[i];
        }

        int answer = Math.max(dp1[money.length - 2][0], Math.max(dp1[money.length - 2][1], Math.max(dp2[money.length - 1][0], dp2[money.length - 1][1])));
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        Assertions.assertThat(solution.solution(new int[]{1, 2, 3, 1})).isEqualTo(4);
        Assertions.assertThat(solution.solution(new int[]{1, 2, 3})).isEqualTo(3);
        Assertions.assertThat(solution.solution(new int[]{10, 1, 2, 3})).isEqualTo(12);
        Assertions.assertThat(solution.solution(new int[]{10, 1, 2})).isEqualTo(10);
        Assertions.assertThat(solution.solution(new int[]{1000, 0, 0, 1000, 0, 0, 1000, 0, 0, 1000})).isEqualTo(3000);
        Assertions.assertThat(solution.solution(new int[]{91, 90, 5, 7, 5, 7})).isEqualTo(104);
        Assertions.assertThat(solution.solution(new int[]{90, 0, 0, 95, 1, 1})).isEqualTo(185);
    }
}