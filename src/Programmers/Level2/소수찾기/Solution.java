// https://programmers.co.kr/learn/courses/30/lessons/42839
package Programmers.Level2.소수찾기;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int solution(String numbers) {

        // 소수의 배수는 소수아님
        // p^2 > n 을 처음으로 만족하는 p의 배수까지만 지워가면됨.
        int n = Integer.parseInt(Arrays.stream(numbers.split(""))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining()));
        int[] arr = new int[n + 1]; // 소수면 0
        arr[0] = 1;
        arr[1] = 1;
        int p = (int) Math.sqrt(n) + 1;

        for (int i = 2; i <= p; i++) {
            int m = 2;
            while (i * m <= n) {
                if (arr[i * m] != 1)
                    arr[i * m] = 1;
                m++;
            }
        }


        Set<Integer> set = new HashSet<>();
        permutation(numbers, "", 0, new boolean[numbers.length()], set);

        int answer = 0;
        final Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            final Integer next = iterator.next();
            if (arr[next] == 0)
                answer++;
        }
        return answer;
    }

    // 순열
    static void permutation(String numbers, String str, int depth, boolean[] visited, Set<Integer> set) {
        if (!"".equals(str))
            set.add(Integer.parseInt(str));

        if (depth == numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                permutation(numbers, str + numbers.charAt(i), depth + 1, visited, set);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("17"));
        System.out.println(solution("011"));
//        System.out.println(solution("2340"));
//        System.out.println(solution("0"));
//        System.out.println(solution("1"));
//        System.out.println(solution("1111111"));
//        System.out.println(solution("1357912"));
    }
}
