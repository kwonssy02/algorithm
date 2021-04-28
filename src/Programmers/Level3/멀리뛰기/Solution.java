// https://programmers.co.kr/learn/courses/30/lessons/12914
package Programmers.Level3.멀리뛰기;

// n거리를 뛰는 방법은 n-1거리에서 1칸 뛰는 수 + n-2 거리에서 2칸 뛰는 수 (fibonacci)
class Solution {
    public long solution(int n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;

        long[] fibonacci = new long[n + 1];
        fibonacci[1] = 1; // 1
        fibonacci[2] = 2; // 1+1, 2

        for (int i = 3; i <= n; i++) {
            fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % 1234567;
        }

        return fibonacci[n];
    }

    public static void main(String[] args) {
        new Solution().solution(4);
        new Solution().solution(3);
        new Solution().solution(1);
    }
}