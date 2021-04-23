// https://programmers.co.kr/learn/courses/30/lessons/17680
package Programmers.Level2._1차_캐시;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> queue = new LinkedList<>();
        int answer = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            boolean cacheHit = false;
            for (final String s : queue) {
                if (s.equals(city)) {
                    cacheHit = true;
                    queue.remove(s);
                    break;
                }
            }
            answer += (cacheHit ? 1 : 5);
            queue.add(city);
            if (queue.size() > cacheSize)
                queue.poll();
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

        solution.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        solution.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
    }
}
