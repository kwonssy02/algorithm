// https://programmers.co.kr/learn/courses/30/lessons/12927
package Programmers.Level3.야근지수;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        // 남은 작업의 평균을 맞추어라... 큰수부터 pq로?

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            queue.add(work);
        }

        while (!queue.isEmpty()) {
            final Integer poll = queue.poll();
            if (poll - 1 > 0)
                queue.add(poll - 1);
            n--;
            if (n == 0)
                break;
        }

        long answer = 0;
        for (Integer number : queue) {
            answer += number * number;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(4, new int[]{4, 3, 3}));
        System.out.println(new Solution().solution(1, new int[]{2, 1, 2}));
        System.out.println(new Solution().solution(3, new int[]{1,1}));
    }
}