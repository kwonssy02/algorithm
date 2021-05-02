// https://programmers.co.kr/learn/courses/30/lessons/67258
package Programmers.Level3.보석쇼핑;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        Set<String> set = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        int start = 0;
        int length = 0;
        int minStart = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < gems.length; i++) {
            String gem = gems[i];

            map.put(gem, map.getOrDefault(gem, 0) + 1);
            queue.add(gem);
            length++;

            while (map.get(queue.peek()) > 1) {
                String poll = queue.poll();
                length--;
                start++;

                Integer count = map.get(poll);
                if (count == 1)
                    map.remove(poll);
                else
                    map.put(poll, count - 1);
            }

            if (set.size() == map.size() && minLength > length) {
                minLength = length;
                minStart = start;
            }

        }

        int[] answer = new int[]{minStart + 1, minStart + minLength};
//        System.out.println((minStart+1) + " " + (minStart+minLength));
        return answer;
    }

    public static void main(String[] args) {
        new Solution().solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
        new Solution().solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
        new Solution().solution(new String[]{"XYZ", "XYZ", "XYZ"});
        new Solution().solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
        new Solution().solution(new String[]{"AA", "BB", "BB", "CC", "CC", "CC"});
    }
}
