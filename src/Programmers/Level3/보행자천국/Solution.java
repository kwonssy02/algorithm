// https://programmers.co.kr/learn/courses/30/lessons/1832
package Programmers.Level3.보행자천국;

class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    if (i - 1 >= 0 && cityMap[i - 1][j] == 0) {
                        dp[i][j] += dp[i - 1][j];
                    }

                    if (j - 1 >= 0 && cityMap[i][j - 1] == 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                } else if (cityMap[i][j] == 1) {
                    continue;
                } else if (cityMap[i][j] == 2) {
                    if (i + 1 < m) {
                        for (int ti = (i - 1); ti >= 0; ti--) {
                            if (cityMap[ti][j] == 0) {
                                dp[i + 1][j] += dp[ti][j];
                                break;
                            } else if (cityMap[ti][j] == 1)
                                break;
                        }
                    }
                    if (j + 1 < n) {
                        for (int tj = (j - 1); tj >= 0; tj--) {
                            if (cityMap[i][tj] == 0) {
                                dp[i][j + 1] += dp[i][tj];
                                break;
                            } else if (cityMap[i][tj] == 1)
                                break;
                        }
                    }
                }

                dp[i][j] %= MOD;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, 3, new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 0, 0},
                new int[]{0, 0, 0},
        }));

        System.out.println(new Solution().solution(3, 6, new int[][]{
                new int[]{0, 2, 0, 0, 0, 2},
                new int[]{0, 0, 2, 0, 1, 0},
                new int[]{1, 0, 0, 2, 2, 0},
        }));
    }
}