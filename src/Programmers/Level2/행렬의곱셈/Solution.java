// https://programmers.co.kr/learn/courses/30/lessons/12949
package Programmers.Level2.행렬의곱셈;

class Solution {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                int temp = 0;
                for (int k = 0; k < arr1[i].length; k++) {
                    temp += arr1[i][k] * arr2[k][j];
                }
                answer[i][j] = temp;
            }
        }

//        for (int i = 0; i < answer.length; i++) {
//            for (int j = 0; j < answer[i].length; j++) {
//                System.out.print(answer[i][j] + " ");
//            }
//            System.out.println();
//        }

        return answer;
    }

    public static void main(String[] args) {
        solution(
                new int[][]{
                        new int[]{1, 4},
                        new int[]{3, 2},
                        new int[]{4, 1}},
                new int[][]{
                        new int[]{3, 3},
                        new int[]{3, 3}}
        );
        solution(
                new int[][]{
                        new int[]{1, 2},
                        new int[]{3, 4}},
                new int[][]{
                        new int[]{1, 2},
                        new int[]{3, 4}}
        );
//        [[15, 15], [15, 15], [15, 15]]

        // 1 4      3 3
        // 3 2      3 3
        // 4 1


        // [[2, 3, 2],      [[5, 4, 3],
        // [4, 2, 4],       [2, 4, 1],
        // [3, 1, 4]],      [3, 1, 1]]

        // 1 2   1 2
        // 3 4   3 4
    }
}