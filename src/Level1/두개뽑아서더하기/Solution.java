// https://programmers.co.kr/learn/courses/30/lessons/68644
package Level1.두개뽑아서더하기;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int[] solution(int[] numbers) {
        
        Set<Integer> set = new TreeSet<>();
        
        for(int i=0; i< numbers.length; i++) {
            for(int j=i+1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        Iterator<Integer> iter = set.iterator();
        int[] answer = new int[set.size()];
        int i = 0;
        while(iter.hasNext()) {
            answer[i++] = iter.next();
        }
        
        return answer;
    }
}
