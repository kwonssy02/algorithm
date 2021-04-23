// https://programmers.co.kr/learn/courses/30/lessons/17677
package Programmers.Level2.뉴스클러스터링;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution("FRANCE", "french"));
        System.out.println(solution.solution("handshake", "shake hands"));
        System.out.println(solution.solution("aa1+aa2", "AAAA12"));
        System.out.println(solution.solution("E=M*C^2", "e=m*c^2"));
        System.out.println(solution.solution("AA", "BB"));
    }

    public int solution(String str1, String str2) {
        Map<String, Integer> map = new HashMap<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        // 교집합 개수: str1 원소를 다 map에 넣고 str2 원소 돌며 중복 제거된 개수
        // 합집합 개수: str1 원소 개수 + str2 원소 개수 - 교집합 개수

        int str1Count = 0;
        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i), c2 = str1.charAt(i + 1);
            if (!('a' <= c1 && c1 <= 'z') || !('a' <= c2 && c2 <= 'z')) {
                continue;
            }
            str1Count++;
            String key = new String(new char[]{c1, c2});
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int str2Count = 0;
        int intersectionCount = 0;
        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i), c2 = str2.charAt(i + 1);
            if (!('a' <= c1 && c1 <= 'z') || !('a' <= c2 && c2 <= 'z')) {
                continue;
            }
            str2Count++;
            String key = new String(new char[]{c1, c2});
            final Integer count = map.getOrDefault(key, 0);
            if (count > 0) {
                intersectionCount++;
                map.put(key, count - 1);
            }
        }

//        System.out.println("str1Count = " + str1Count);
//        System.out.println("str2Count = " + str2Count);
//        System.out.println("intersectionCount = " + intersectionCount);

        if (str1Count + str2Count - intersectionCount == 0)
            return 65536;
        return intersectionCount * 65536 / (str1Count + str2Count - intersectionCount);
    }
}
