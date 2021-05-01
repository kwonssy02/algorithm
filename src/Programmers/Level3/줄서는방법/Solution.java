// https://programmers.co.kr/learn/courses/30/lessons/12936
package Programmers.Level3.줄서는방법;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        long[] factorials = new long[n + 1];
        factorials[1] = 1;
        for (int i = 2; i <= n-1; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        int[] answer = new int[n];
        int answerIndex = 0;
        int temp = n - 1;
        while (k > 0) {
            int numberIndex = 0;
            while (k > factorials[temp]) {
                k -= factorials[temp];
                numberIndex++;
            }

            answer[answerIndex++] = numbers.remove(numberIndex);
            temp--;
            if(temp == 0) {
                answer[answerIndex] = numbers.get(0);
                break;
            }
        }

//        for (int i = 0; i < answer.length; i++) {
//            System.out.print(answer[i] + " ");
//        }

        return answer;
    }

    public static void main(String[] args) {
        new Solution().solution(3, 5);
        new Solution().solution(20, 10);
    }
}