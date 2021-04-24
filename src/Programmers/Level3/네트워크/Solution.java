// https://programmers.co.kr/learn/courses/30/lessons/43162
package Programmers.Level3.네트워크;

class Solution {
    public int solution(int n, int[][] computers) {

        int answer = 0;
        boolean[] visited = new boolean[computers.length];
        for (int i = 0; i < computers.length; i++) {
            answer += dfs(i, computers, visited);
        }
        return answer;
    }

    public int dfs(int vertex, int[][] edges, boolean[] visited) {
        if (visited[vertex])
            return 0;

        visited[vertex] = true;

        for (int i = 0; i < edges[vertex].length; i++) {
            if (i == vertex) continue;
            if (edges[vertex][i] == 1 && !visited[i]) {
                dfs(i, edges, visited);
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(3, new int[][]{
                        new int[]{1, 1, 0},
                        new int[]{1, 1, 0},
                        new int[]{0, 0, 1}
                }
        ));
        System.out.println(solution.solution(3, new int[][]{
                        new int[]{1, 1, 0},
                        new int[]{1, 1, 1},
                        new int[]{0, 1, 1}
                }
        ));
    }
}