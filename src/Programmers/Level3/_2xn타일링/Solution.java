// https://programmers.co.kr/learn/courses/30/lessons/12900
package Programmers.Level3._2xn타일링;

class Solution {
    // 1, 2만 사용해서 n을 만드는 경우의 수 구하기..
    public int solution(int n) {

        int answer = 0;
        for (int i = 0; i <= n/2; i++) {
            int numOfOne = n - (i*2);
            int numOfTwo = i;
            // n C numOfTwo (n = numOfOne + numOfTwo)
            // = n * n-1 * ... * (n-numOfTwo+1) / numOfTwo!
            System.out.println("numOfOne = " + numOfOne);
            System.out.println("numOfTwo = " + numOfTwo);
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(4);
    }
}