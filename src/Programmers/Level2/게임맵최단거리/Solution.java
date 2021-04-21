// https://programmers.co.kr/learn/courses/30/lessons/1844
package Programmers.Level2.게임맵최단거리;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    boolean[][] visited;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                new int[]{1, 0, 1, 1, 1},
                new int[]{1, 0, 1, 0, 1},
                new int[]{1, 0, 1, 1, 1},
                new int[]{1, 1, 1, 0, 1},
                new int[]{0, 0, 0, 0, 1}
        }));

        System.out.println(solution.solution(new int[][]{
                new int[]{1, 0, 1, 1, 1},
                new int[]{1, 0, 1, 0, 1},
                new int[]{1, 0, 1, 1, 1},
                new int[]{1, 1, 1, 0, 0},
                new int[]{0, 0, 0, 0, 1}
        }));
    }

    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        visited[0][0] = true;

        Queue<C> queue = new LinkedList<>();
        queue.add(new C(0, 0, 1));

        int n = maps.length - 1;
        int m = maps[0].length - 1;
        while (!queue.isEmpty()) {
            final C c = queue.poll();
            if (c.x == n && c.y == m) return c.step;
            if (c.x + 1 <= n && maps[c.x + 1][c.y] == 1 && !visited[c.x + 1][c.y]) {
                visited[c.x + 1][c.y] = true;
                queue.add(new C(c.x + 1, c.y, c.step + 1));
            }
            if (c.x - 1 >= 0 && maps[c.x - 1][c.y] == 1 && !visited[c.x - 1][c.y]) {
                visited[c.x - 1][c.y] = true;
                queue.add(new C(c.x - 1, c.y, c.step + 1));
            }
            if (c.y + 1 <= m && maps[c.x][c.y + 1] == 1 && !visited[c.x][c.y + 1]) {
                visited[c.x][c.y + 1] = true;
                queue.add(new C(c.x, c.y + 1, c.step + 1));
            }
            if (c.y - 1 >= 0 && maps[c.x][c.y - 1] == 1 && !visited[c.x][c.y - 1]) {
                visited[c.x][c.y - 1] = true;
                queue.add(new C(c.x, c.y - 1, c.step + 1));
            }
        }

        return -1;
    }

    class C {
        int x;
        int y;
        int step;

        public C(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}