// https://programmers.co.kr/learn/courses/30/lessons/12924
package Programmers.Level2.숫자의표현;

//TODO: '주어진 자연수를 연속된 자연수의 합으로 표현하는 방법의 수는 주어진 수의 홀수 약수의 개수와 같다.'
class Solution {
    public static int solution(int n) {

        boolean isOddNumber = (n % 2) == 1;
        int answer = 0;
        for (int i = 1; i <= n * 2; i++) {
            if ((n * 2) % i == 0) {
                if ((isOddNumber && i % 2 == 0) || (!isOddNumber && i % 2 == 1))
                    answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(15));
        System.out.println(solution(20));
    }
}