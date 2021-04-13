// https://programmers.co.kr/learn/courses/30/lessons/12954
package Level1.x만큼간격이있는n개의숫자;

class Solution {
    public static long[] solution(int x, int n) {
        long[] answer = new long[n];
        long acc = x;
        for (int i = 0; i < n; i++) {
            answer[i] = acc;
            acc += x;
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(2,5);
    }
}
