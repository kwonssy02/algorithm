// https://programmers.co.kr/learn/courses/30/lessons/12906
package Programmers.Level1.같은숫자는싫어;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[] solution(int[] arr) {

        List<Integer> list = new ArrayList<>();
        int before = 10;
        for (int i = 0; i < arr.length; i++) {
            if (before != arr[i])
                list.add(arr[i]);
            before = arr[i];
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
