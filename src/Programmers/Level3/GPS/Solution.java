// https://programmers.co.kr/learn/courses/30/lessons/1837
package Programmers.Level3.GPS;

import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            edges.put(i, list);
        }

        for (int[] edge : edge_list) {
            edges.get(edge[0]).add(edge[1]);
            edges.get(edge[1]).add(edge[0]);
        }

        int[][] dp = new int[k][n + 1]; // i번째 gpslog의 vertex가 j일 때, 최소 수정횟수
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][gps_log[0]] = 0;

        for (int i = 0; i < k - 1; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) continue;
                List<Integer> vertexes = edges.get(j);
                for (Integer vertex : vertexes) {
                    if (dp[i + 1][vertex] > dp[i][j] + (gps_log[i + 1] == vertex ? 0 : 1)) { // gpslog와 vertex가 일치할 경우 0을 더하고, 아니면 1을 더함
                        dp[i + 1][vertex] = dp[i][j] + (gps_log[i + 1] == vertex ? 0 : 1);
                    }
                }
            }
        }

        return dp[k - 1][gps_log[k - 1]] == Integer.MAX_VALUE ? -1 : dp[k - 1][gps_log[k - 1]];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(7, 10, new int[][]{
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3},
                new int[]{2, 4},
                new int[]{3, 4},
                new int[]{3, 5},
                new int[]{4, 6},
                new int[]{5, 6},
                new int[]{5, 7},
                new int[]{6, 7}
        }, 6, new int[]{1, 2, 3, 3, 6, 7}));

        System.out.println(new Solution().solution(7, 10, new int[][]{
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3},
                new int[]{2, 4},
                new int[]{3, 4},
                new int[]{3, 5},
                new int[]{4, 6},
                new int[]{5, 6},
                new int[]{5, 7},
                new int[]{6, 7}
        }, 6, new int[]{1, 2, 4, 6, 5, 7}));

        System.out.println(new Solution().solution(7, 10, new int[][]{
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3},
                new int[]{2, 4},
                new int[]{3, 4},
                new int[]{3, 5},
                new int[]{4, 6},
                new int[]{5, 6},
                new int[]{5, 7},
                new int[]{6, 7}
        }, 6, new int[]{1, 1, 1, 1, 1, 7}));
    }
}