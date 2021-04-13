// https://programmers.co.kr/learn/courses/30/lessons/12910
package Level1.나누어떨어지는숫자배열;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] arr, int divisor) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) 
                list.add(arr[i]);
        }

        if (list.size() == 0)
            return new int[]{-1};
           
        return list.stream().sorted().mapToInt(e -> e).toArray();
    }
}
