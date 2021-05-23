// https://programmers.co.kr/learn/courses/30/lessons/42891
package Programmers.Level4.무지의먹방라이브;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {
    public int solution(int[] food_times, long k) {
        k++;
        long sum = 0;
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            q.add(food_times[i]);
            sum += food_times[i];
        }

        if (k > sum) return -1;

        long round = 0;
        while (true) {
            if (k <= q.size()) {
                int temp = 0;
                for (int i = 0; i < food_times.length; i++) {
                    if (food_times[i] > round) {
                        temp++;
                        if (temp == k)
                            return i + 1;
                    }
                }
            }
            round++;
            k -= q.size();
            while (!q.isEmpty() && q.peek() == round) q.poll();
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        assertThat(solution.solution(new int[]{3, 1, 2}, 5)).isEqualTo(1);
        assertThat(solution.solution(new int[]{4, 3, 2, 1}, 9)).isEqualTo(1);
        assertThat(solution.solution(new int[]{1, 1, 1, 1}, 3)).isEqualTo(4);
        assertThat(solution.solution(new int[]{5}, 6)).isEqualTo(-1);
    }
}