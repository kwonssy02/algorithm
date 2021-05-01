// https://programmers.co.kr/learn/courses/30/lessons/12987
package Programmers.Level3.숫자게임;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            b.add(B[i]);
        }

        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            int f = binarySearch(A[i], b);
            if (f == b.size()) break;
            b.remove(f);
            answer++;
        }

        return answer;
    }

    public int binarySearch(int number, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= number) {
                left = mid + 1;
            } else if (list.get(mid) > number) {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
        System.out.println(new Solution().solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
    }
}