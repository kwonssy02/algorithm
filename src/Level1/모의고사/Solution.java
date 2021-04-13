// https://programmers.co.kr/learn/courses/30/lessons/42840
package Level1.모의고사;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {

        int first, second, third;
        int[] secondArr = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thirdArr = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        first = second = third = 0;
        for(int i=0; i<answers.length; i++) {
            if(i%5+1 == answers[i]) first++;
            if(secondArr[i%8] == answers[i]) second++;
            if(thirdArr[i%10] == answers[i]) third++;
        }

        List<Integer> list = new ArrayList<>();
        int max = Math.max(first, Math.max(second, third));
        if(max == first) list.add(1);
        if(max == second) list.add(2);
        if(max == third) list.add(3);
        return list.stream().mapToInt(i->i).toArray();
    }
}
