// https://programmers.co.kr/learn/courses/30/lessons/17684
package Programmers.Level2._3차_압축;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> index = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            index.put(Character.toString((char) ('A' + (i - 1))), i);
        }

        int maxIndexNumber = 26;

        for (int i = 0; i < msg.length(); i++) {
            String foundIndex = null;
            for (int j = i + 1; j <= msg.length(); j++) {
                String temp = msg.substring(i, j);
                if (index.get(temp) == null) {
                    index.put(temp, ++maxIndexNumber);
                    i = j - 2;
                    break;
                }
                foundIndex = temp;
                if (j == msg.length())
                    i = msg.length();
            }
            list.add(index.get(foundIndex));
        }

//        for (final Map.Entry<String, Integer> entry : index.entrySet()) {
//            System.out.println(entry);
//        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
//            System.out.print(answer[i] + " ");
        }
//        System.out.println();
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution("KAKAO");
        solution.solution("TOBEORNOTTOBEORTOBEORNOT");
        solution.solution("ABABABABABABABAB");
    }
}
