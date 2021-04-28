// https://programmers.co.kr/learn/courses/30/lessons/42895
package Programmers.Level3.N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(null);
        for (int i = 1; i <= 8; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(makeNN(N, i));
            dp.add(set);
        }
        // dp[1] = N
        // dp[2] = NN, N+N, N-N, N*N, N/N

        for (int i = 1; i <= 8; i++) {
            Set<Integer> newSet = new HashSet<>();

            for (int j = 1; j <= i / 2; j++) {
//                System.out.println(j + " " + (i-j));
                for (Integer a : dp.get(j)) {
                    for (Integer b : dp.get(i - j)) {
                        if (a + b <= 32000)
                            newSet.add(a + b);
                        if (1 <= a - b)
                            newSet.add(a - b);
                        if (1 <= b - a)
                            newSet.add(b - a);
                        if (a * b <= 32000)
                            newSet.add(a * b);
                        if (b != 0)
                            newSet.add(a / b);
                        if (a != 0)
                            newSet.add(b / a);
                    }
                }
            }

            for (int j = 1; j <= i - 1; j++) {
                newSet.removeAll(dp.get(j));
            }

            dp.get(i).addAll(newSet);

//            System.out.println("i = " + i);
//            dp.get(i).stream().forEach(e -> {
//                System.out.print(e + " ");
//            });
//            System.out.println();
//            System.out.println();

            if (dp.get(i).contains(number))
                return i;
        }

        return -1;
    }

    public int makeNN(int N, int length) {
        int temp = N;
        while (--length > 0) {
            temp *= 10;
            temp += N;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, 12));
        System.out.println(new Solution().solution(2, 11));
        System.out.println(new Solution().solution(5, 55));
//        System.out.println(new Solution().solution(4, 17));
//        System.out.println(new Solution().solution(1, 251));
//        System.out.println(new Solution().solution(5, 5));
//        System.out.println(new Solution().solution(5, 6200));
        System.out.println(new Solution().solution(9, 198));
    }
}