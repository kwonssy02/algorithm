// https://programmers.co.kr/learn/courses/30/lessons/42747
package Programmers.Level2.HIndex;

import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        for (int h = citations[citations.length - 1]; h > 0; h--) {
            int finalH = h;
            if (Arrays.stream(citations).filter(e -> e >= finalH).count() >= h) {
                return h;
            }
        }
        return 0;
    }
}
