// https://programmers.co.kr/learn/courses/30/lessons/42576
package Level1.완주하지못한선수;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<participant.length; i++) {
            Integer e = map.get(participant[i]);
            if(e == null) {
                map.put(participant[i], 1);
            } else {
                map.put(participant[i], e+1);
            }
        }
        for(int i=0; i<completion.length; i++) {
            Integer e = map.get(completion[i]);
            if(e == 1)
                map.remove(completion[i]);
            else
                map.put(completion[i], e-1);
        }

        Iterator<String> iter = map.keySet().iterator();
        return iter.next();
        
        
        
    }
}
