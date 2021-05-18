// https://programmers.co.kr/learn/courses/30/lessons/1837
package Programmers.Level3.GPS;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        boolean[][] edges = new boolean[n + 1][n + 1];
        for (int i = 1; i < edges.length; i++) {
            edges[i][i] = true;
        }
        for (int[] edge : edge_list) {
            edges[edge[0]][edge[1]] = true;
            edges[edge[1]][edge[0]] = true;
        }

        int count = 0;
        for (int i = 1; i < k - 1; i++) {
            // 이전 거점에서 현재 거점, 현재 거점에서 이후 거점으로 이동 불가할 경우,
            if (!edges[gps_log[i - 1]][gps_log[i]] || !edges[gps_log[i]][gps_log[i + 1]]) {
                boolean updated = false;
                for (int j = 1; j <= n; j++) {
                    if (j == i) continue;
                    if (edges[gps_log[i - 1]][j] && edges[j][gps_log[i + 1]]) {
                        gps_log[i] = j;
                        updated = true;
                        count++;
                        break;
                    }
                }

                if (!updated)
                    return -1;
            }
        }

        return count;
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
    }
}