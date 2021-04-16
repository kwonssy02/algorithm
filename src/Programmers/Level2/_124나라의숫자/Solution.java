// https://programmers.co.kr/learn/courses/30/lessons/12899
package Programmers.Level2._124나라의숫자;

class Solution {
    public static String solution(int n) {

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            if (n % 3 == 0) {
                sb.append(4);
            } else {
                sb.append(n % 3);
            }
            n = (n - 1) / 3;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(10));
    }
}

// 10   3   124
// 1    1   1
// 2    2   2
// 3    10  4
// 4    11  11
// 5    12  12
// 6    20  14
// 7    21  21
// 8    22  22
// 9    100 24
// 10   101 41
// 11   102 42
// 12   110 44
// 13   111 111

