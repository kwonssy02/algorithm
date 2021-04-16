// https://programmers.co.kr/learn/courses/30/lessons/62048
package Programmers.Level2.멀쩡한사각형;

class Solution {
    public static long solution(int w, int h) {
        return (long) w * h - (w + h - gcd(w, h));
    }

    public static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (true) {
            int temp = a % b;
            if (temp == 0)
                return b;
            a = b;
            b = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(1, 1));
        System.out.println(solution(4, 4));
        System.out.println(solution(8, 12));
        System.out.println(solution(10, 1));
        System.out.println(solution(10, 2));
        System.out.println(solution(100000000, 100000000));
    }
}