// https://programmers.co.kr/learn/courses/30/lessons/12943
package Programmers.Level1.콜라츠추측;

class Solution {
    public static int solution(int num) {

        int count = 0;
        long temp = num;
        while (count < 500) {
            if (temp == 1) return count;
            if (temp % 2 == 0) {
                temp /= 2;
            } else {
                temp = 3 * temp + 1;
            }
            // System.out.println(temp);
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(6));
        System.out.println(solution(16));
        System.out.println(solution(626331));
    }
}
