// https://programmers.co.kr/learn/courses/30/lessons/12978
package Programmers.Level2.배달;

import java.util.Arrays;

//TODO: Dijkstra (다익스트라) Priority Queue 로 O(nlogn) 만들기
// https://hsp1116.tistory.com/42
class Solution {

    // O(n^2)
    int[][] path;
    boolean[] visited;
    int[] distance;

    public int solution(int N, int[][] roads, int K) {
        visited = new boolean[N + 1];
        distance = new int[N + 1];
        path = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
            path[i][i] = 0;
        }

        for (int i = 0; i < roads.length; i++) {
            int[] road = roads[i];
            int source = road[0], target = road[1], length = road[2];
            if (path[source][target] > length) {
                path[source][target] = length;
                path[target][source] = length;
            }
        }

        dijkstra(N, 1);
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= K) {
                answer++;
            }
        }
        return answer;
    }

    public void dijkstra(int N, int source) {
        visited[source] = true;
        for (int i = 1; i <= N; i++) {
            distance[i] = path[source][i];
        }

        for (int i = 1; i <= N - 1; i++) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            // 가장 짧은 거리 정점 찾기
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && distance[j] < min) {
                    min = distance[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = true;

            for (int j = 1; j <= N; j++) {
                if (!visited[j]
                        && path[minIndex][j] != Integer.MAX_VALUE
                        && distance[minIndex] + path[minIndex][j] < distance[j]) {
                    distance[j] = distance[minIndex] + path[minIndex][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        long before = System.nanoTime();
        System.out.println(solution.solution(5, new int[][]{
                new int[]{1, 2, 1},
                new int[]{2, 3, 3},
                new int[]{5, 2, 2},
                new int[]{1, 4, 2},
                new int[]{5, 3, 1},
                new int[]{5, 4, 2}
        }, 3));
        long after = System.nanoTime();
        System.out.println(after - before);

        before = System.nanoTime();
        System.out.println(solution.solution(6, new int[][]{
                new int[]{1, 2, 1},
                new int[]{1, 3, 2},
                new int[]{2, 3, 2},
                new int[]{3, 4, 3},
                new int[]{3, 5, 2},
                new int[]{3, 5, 3},
                new int[]{5, 6, 1}
        }, 4));
        after = System.nanoTime();
        System.out.println(after - before);
    }
}
