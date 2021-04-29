// https://programmers.co.kr/learn/courses/30/lessons/12938
package Programmers.Level3.최고의집합;

class Solution {
    public int[] solution(int n, int s) {
        // s 이하이면서 n의 배수 중 최대값을 찾으면 됨..
        if (s / n == 0)
            return new int[]{-1};

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s / n;
        }
        for (int i = arr.length - s % n; i < arr.length; i++) {
            arr[i]++;
        }

        return arr;
    }

    public static void main(String[] args) {
        new Solution().solution(2, 9);
        new Solution().solution(2, 1);
        new Solution().solution(2, 8);
    }
}