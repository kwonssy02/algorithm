// https://programmers.co.kr/learn/courses/30/lessons/42578
package Programmers.Level2.위장;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            map.put(cloth[1], map.get(cloth[1]) == null ? 1 : map.get(cloth[1]) + 1);
        }

        final Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        int answer = 1;
        while (iterator.hasNext()) {
            final Map.Entry<String, Integer> next = iterator.next();
            answer *= next.getValue() + 1;
        }
        answer--;
        return answer;
    }
}
