// https://programmers.co.kr/learn/courses/30/lessons/12935
package Programmers.Level1.제일작은수제거하기;

class Solution {
    public int[] solution(int[] arr) {

        if (arr.length == 1)
            return new int[]{-1};

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        int[] answer = new int[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (min != arr[i])
                answer[k++] = arr[i];
        }

        return answer;
    }
}
