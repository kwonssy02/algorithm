// https://programmers.co.kr/learn/courses/30/lessons/42626
package Programmers.Level2.더맵게;

import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : scoville) {
            queue.add(i);
        }

        int answer = 0;
        while (true) {
            final Integer minScoville = queue.poll();
            if (minScoville >= K)
                break;

            if (queue.size() == 0)
                return -1;

            final Integer minScoville2 = queue.poll();
            queue.add(minScoville + minScoville2 * 2);
            answer++;
        }
        return answer;
    }
}
