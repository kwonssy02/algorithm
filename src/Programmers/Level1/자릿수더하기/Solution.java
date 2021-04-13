// https://programmers.co.kr/learn/courses/30/lessons/12931
package Programmers.Level1.자릿수더하기;

public class Solution {
    public int solution(int n) {

        int answer = 0;
        while (n > 0) {
            answer += n % 10;
            n /= 10;
        }

        return answer;
    }
}
