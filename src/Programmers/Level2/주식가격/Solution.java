// https://programmers.co.kr/learn/courses/30/lessons/42584
package Programmers.Level2.주식가격;

class Solution {
    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j <= prices.length; j++) {
                if (j == prices.length) {
                    answer[i] = prices.length - 1 - i;
                    break;
                }
                if (prices[i] > prices[j]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }
}
