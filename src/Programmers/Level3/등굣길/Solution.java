// https://programmers.co.kr/learn/courses/30/lessons/42898
package Programmers.Level3.등굣길;

// "오른쪽과 아래쪽으로만 움직이는" 것이 키 포인트
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m][n];

        for (int[] puddle : puddles) {
            map[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        map[0][0] = 1;
        for (int mi = 0; mi < map.length; mi++) {
            for (int ni = 0; ni < map[mi].length; ni++) {
                if (mi == 0 && ni == 0) continue;
                if (map[mi][ni] == -1)
                    continue;
                int left = (mi - 1 < 0 || map[mi - 1][ni] == -1 ? 0 : map[mi - 1][ni]);
                int up = (ni - 1 < 0 || map[mi][ni - 1] == -1 ? 0 : map[mi][ni - 1]);
                map[mi][ni] = (left + up) % 1000000007;
            }
        }

        return map[m - 1][n - 1];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(4, 3, new int[][]{
                new int[]{2, 2}
        }));
        System.out.println(solution.solution(100, 100, new int[][]{
                new int[]{2, 2},
                new int[]{4, 10},
                new int[]{5, 10},
                new int[]{10, 20},
                new int[]{15, 15},
                new int[]{18, 18},
        }));
    }
}
