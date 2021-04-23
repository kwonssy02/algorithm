// https://programmers.co.kr/learn/courses/30/lessons/17687
package Programmers.Level2._3차_n진수게임;

import java.util.ArrayList;
import java.util.List;

//TODO: Integer.toString 활용한 n진법 변환 살펴보기
class Solution {
    public String solution(int n, int t, int m, int p) {
        List<Character> list = new ArrayList<>();
        char[] notationNumbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        int number = 0;
        list.add('0');
        while (list.size() < m * t) {
            int temp = number;
            List<Character> nums = new ArrayList<>();
            while (temp > 0) {
                nums.add(notationNumbers[temp % n]);
                temp /= n;
            }

            for (int i = nums.size() - 1; i >= 0; i--) {
                list.add(nums.get(i));
            }

            number++;
        }

//        for (final Character character : list) {
//            System.out.println(character + " ");
//        }

        StringBuilder sb = new StringBuilder();
        for (int i = p - 1; i < m * t; i += m) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(2, 4, 2, 1));
//        solution.solution(16, 16, 2, 1);
//        solution.solution(16, 16, 2, 2);
    }
}
