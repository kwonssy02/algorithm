// https://programmers.co.kr/learn/courses/30/lessons/43105
package Programmers.Level3.정수삼각형;

class Solution {
    public int solution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(
                        j == triangle[i].length - 1 ? 0 : triangle[i - 1][j],
                        j == 0 ? 0 : triangle[i - 1][j - 1]
                );
            }
        }

        int answer = 0;
        int depth = triangle.length - 1;
        for (int i = 0; i <= depth; i++) {
            if (answer < triangle[depth][i])
                answer = triangle[depth][i];
        }

        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                new int[]{7},
                new int[]{3, 8},
                new int[]{8, 1, 0},
                new int[]{2, 7, 4, 4},
                new int[]{4, 5, 2, 6, 5}
        }));
    }
}