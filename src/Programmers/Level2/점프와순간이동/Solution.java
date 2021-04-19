// https://programmers.co.kr/learn/courses/30/lessons/12980
package Programmers.Level2.점프와순간이동;

public class Solution {
    public static int solution(int n) {
        int ans = 0;
        while (n > 0) {
            if (n % 2 == 1)
                ans++;
            n /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(solution(5)); // 101
        System.out.println(solution(6)); // 110
        System.out.println(solution(5000)); //
        System.out.println(solution(1));
    }
}