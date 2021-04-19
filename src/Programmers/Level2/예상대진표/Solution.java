// https://programmers.co.kr/learn/courses/30/lessons/12985
package Programmers.Level2.예상대진표;

class Solution {
    public static int solution(int n, int a, int b) {
        int round = 0;
        while (a != b) {
            round++;
            a = (int) Math.ceil(a / 2.0);
            b = (int) Math.ceil(b / 2.0);
        }

        return round;
    }

    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
    }
}

/*
    N	A	B	answer
    8	4	7	3

    1 2 3 4 5 6 7 8
    1   2   3   4       1R
    1       2           2R
    1                   3R

    4 = 2^2
    7 = 2^3
 */