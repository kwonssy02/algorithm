// https://programmers.co.kr/learn/courses/30/lessons/1829
package Programmers.Level2.카카오프렌즈컬러링북;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(6, 4, new int[][]{
                new int[]{1, 1, 1, 0},
                new int[]{1, 2, 2, 0},
                new int[]{1, 0, 0, 1},
                new int[]{0, 0, 0, 1},
                new int[]{0, 0, 0, 3},
                new int[]{0, 0, 0, 3}
        });
    }

    public int[] solution(int m, int n, int[][] picture) {

        boolean[][] visited = new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0) continue;
                int areaBlocks = bfs(picture[i][j], i, j, m, n, visited, picture);
                if (areaBlocks > 0) {
                    numberOfArea++;
                    if (maxSizeOfOneArea < areaBlocks)
                        maxSizeOfOneArea = areaBlocks;
                }
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

//        System.out.println("numberOfArea = " + numberOfArea);
//        System.out.println("maxSizeOfOneArea = " + maxSizeOfOneArea);
        return answer;
    }

    public int bfs(int color, int x, int y, int m, int n, boolean[][] visited, int[][] picture) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || picture[x][y] == 0 || picture[x][y] != color)
            return 0;

        visited[x][y] = true;

        return 1 + bfs(color, x + 1, y, m, n, visited, picture)
                + bfs(color, x - 1, y, m, n, visited, picture)
                + bfs(color, x, y + 1, m, n, visited, picture)
                + bfs(color, x, y - 1, m, n, visited, picture);
    }
}

/*

 */
