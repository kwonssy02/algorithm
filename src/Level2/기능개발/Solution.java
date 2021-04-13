// https://programmers.co.kr/learn/courses/30/lessons/42586
package Level2.기능개발;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        for (int i = 0; i < progresses.length; i++) {
            progresses[i] = (100-progresses[i])%speeds[i] == 0 ? (100-progresses[i])/speeds[i] : (100-progresses[i])/speeds[i]+1;  
        }

        List<Integer> list = new ArrayList<>();
        int endDateTemp = progresses[0];
        int temp = 0;
        for (int endDate : progresses) {
            if(endDateTemp >= endDate) {
                temp++;
            } else {
                list.add(temp);
                temp = 1;
                endDateTemp = endDate;
            }
        }
        if(temp > 0)
            list.add(temp);

        return list.stream().mapToInt(e -> e).toArray();
    }
}
