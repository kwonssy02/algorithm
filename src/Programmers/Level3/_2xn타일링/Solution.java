// https://programmers.co.kr/learn/courses/30/lessons/12900
package Programmers.Level3._2xn타일링;

class Solution {
    // nCr로 풀 경우, 60000!를 int/long에 담기 어려움..
    int[] fibonacci = new int[60001];

    // 1, 2만 사용해서 n을 만드는 경우의 수 구하기..
    public int solution(int n) {

        fibonacci[1] = 1;
        fibonacci[2] = 2;

        for (int i = 3; i <= n; i++) {
            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % 1000000007;
        }

        return fibonacci[n];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(1));
        System.out.println(solution.solution(10000));
    }
}