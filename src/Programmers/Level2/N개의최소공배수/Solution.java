// https://programmers.co.kr/learn/courses/30/lessons/12953
package Programmers.Level2.N개의최소공배수;

import java.util.Arrays;

class Solution {
    public static int solution(int[] arr) {

        final int[] noDuplicate = Arrays.stream(arr).distinct().toArray();
        int answer = 1;
        for (int i = 0; i < noDuplicate.length; i++) {
            answer = answer*noDuplicate[i]/gcd(answer, noDuplicate[i]);
        }
        return answer;
    }

    public static int gcd(int a, int b) {
        if(a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while(true) {
            int temp = a%b;
            if(temp == 0) {
                return b;
            }
            a = b;
            b = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 6, 8, 14}));
        System.out.println(solution(new int[]{1, 2, 3}));
    }
}