// https://programmers.co.kr/learn/courses/30/lessons/42862
package Programmers.Level1.체육복;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {

        n -= lost.length;

        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    n++;
                    break;
                }
            }
        }

        for (int i = 0; i < lost.length; i++) {
            if (lost[i] == -1)
                continue;
            for (int j = 0; j < reserve.length; j++) {
                if (reserve[j] != -1 && Math.abs(lost[i] - reserve[j]) == 1) {
                    reserve[j] = -1;
                    n++;
                    break;
                }
            }
        }

        return n;
    }
}
