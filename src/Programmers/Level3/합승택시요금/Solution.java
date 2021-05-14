// https://programmers.co.kr/learn/courses/30/lessons/72413
package Programmers.Level3.합승택시요금;

import java.util.Arrays;

// Graph (Floyd-Warshall)
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        final int INF = 999999999;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i < graph.length; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                for (int k = 1; k < graph.length; k++) {
                    if (j == k) continue;
                    if (graph[j][k] > graph[j][i] + graph[i][k]) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                        graph[k][j] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }

//        for (int i = 1; i < graph.length; i++) {
//            for (int j = 1; j < graph.length; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }

        int minPrice = Integer.MAX_VALUE;
        for (int i = 1; i < graph.length; i++) {
            if(graph[i][a] == INF || graph[i][b] == INF) continue;
            if (minPrice > graph[s][i] + graph[i][a] + graph[i][b]) {
                minPrice = graph[s][i] + graph[i][a] + graph[i][b];
            }
        }

        return minPrice;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, 4, 6, 2, new int[][]{
                new int[]{4, 1, 10},
                new int[]{3, 5, 24},
                new int[]{5, 6, 2},
                new int[]{3, 1, 41},
                new int[]{5, 1, 24},
                new int[]{4, 6, 50},
                new int[]{2, 4, 66},
                new int[]{2, 3, 22},
                new int[]{1, 6, 25},
        }));

        System.out.println(new Solution().solution(7, 3, 4, 1, new int[][]{
                new int[]{5, 7, 9},
                new int[]{4, 6, 4},
                new int[]{3, 6, 1},
                new int[]{3, 2, 3},
                new int[]{2, 1, 6},
        }));

        System.out.println(new Solution().solution(6	,4,	5	,6, new int[][]{
                new int[]{2,6,6},
                new int[]{6,3,7},
                new int[]{4,6,7},
                new int[]{6,5,11},
                new int[]{2,5,12},
                new int[]{5,3,20},
                new int[]{2,4,8},
                new int[]{4,3,9},
        }));
    }
}