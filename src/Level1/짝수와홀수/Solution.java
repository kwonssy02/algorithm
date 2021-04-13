// https://programmers.co.kr/learn/courses/30/lessons/12937
package Level1.짝수와홀수;

class Solution {
    public String solution(int num) {
        if (num == 0 || num % 2 == 0)
            return "Even";
        else
            return "Odd";
    }
}
