// https://programmers.co.kr/learn/courses/30/lessons/12928
package Level1.약수의합;

class Solution {
    public int solution(int n) {

        int answer = n;
        for (int i = n/2; i >= 1; i--) {
            if (n%i == 0)
                answer += i;
        }
        return answer;
    }
}
