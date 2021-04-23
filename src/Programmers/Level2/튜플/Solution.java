// https://programmers.co.kr/learn/courses/30/lessons/64065
package Programmers.Level2.튜플;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public static void main(String[] args) {
        new Solution().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }

    public int[] solution(String s) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(s);

        Map<String, Integer> map = new HashMap<>();
        while (m.find()) {
            String number = m.group();
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        int[] answer = new int[map.size()];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            answer[map.size() - entry.getValue()] = Integer.parseInt(entry.getKey()); // 아름다운 이곳에 주목하라.
        }

        return answer;
    }
}