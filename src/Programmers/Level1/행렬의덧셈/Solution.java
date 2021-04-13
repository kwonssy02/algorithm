// https://programmers.co.kr/learn/courses/30/lessons/12950
package Programmers.Level1.행렬의덧셈;

class Solution {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr1[0].length;

        int[][] answer = new int[row][col];

        for (int i = 0; i < row; i++) {
            int[] temp = new int[col];
            for (int j = 0; j < col; j++) {
                temp[j] = arr1[i][j] + arr2[i][j];
            }
            answer[i] = temp;
            // first[i] = arr1[0][i] + arr2[0][i];
            // second[i] = arr1[1][i] + arr2[1][i];    
        }


        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{new int[]{1}, new int[]{2}}, new int[][]{new int[]{3}, new int[]{4}});
    }
}
