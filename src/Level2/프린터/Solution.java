// https://programmers.co.kr/learn/courses/30/lessons/42587
package Level2.프린터;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> queue = new LinkedList<>();
        for (int priority : priorities) {
            queue.add(priority);
        }

        Arrays.sort(priorities);

        int len = priorities.length;
        int answer = 0;
        while(!queue.isEmpty()) {
            final Integer priority = queue.poll();
            if(priority == priorities[len-answer-1]) {
                answer++;
                location--;
                if(location < 0)
                    break;
            } else {
                queue.add(priority);
                location--;
                if(location < 0)
                    location = queue.size()-1;
            }
        }
        return answer;
    }
}
