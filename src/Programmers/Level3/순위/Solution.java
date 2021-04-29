// https://programmers.co.kr/learn/courses/30/lessons/49191
package Programmers.Level3.순위;

//TODO: Floyd-Warshall 적용
class Solution {
    public int solution(int n, int[][] results) {
        int[][] fights = new int[n + 1][n + 1];

        for (int[] result : results) {
            fights[result[0]][result[1]] = 1;
            fights[result[1]][result[0]] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                arrange(fights, i, j, n);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if(i==j)continue;
                if(fights[i][j] != 0)
                    count++;
            }
            if(count == n-1)
                answer++;
        }

        return answer;
    }

    public void arrange(int[][] fights, int i, int j, int n) {
        if (fights[i][j] == 1) { // i가 j를 이기면, i를 이긴 사람은 j를 이기고, j에게 진 사람은 i에게 짐
            for (int k = 1; k <= n; k++) {
                if (k == i) continue;
                if (fights[k][i] == 1) {
                    fights[k][j] = 1;
                    fights[j][k] = -1;
                }
                if (fights[k][j] == -1) {
                    fights[i][k] = 1;
                    fights[k][i] = -1;
                }
            }
        } else if (fights[i][j] == -1) { // i가 j에게 졌으면, j를 이긴 사람은 i를 이기고, i에게 진 사람은 j에게 짐
            for (int k = 1; k <= n; k++) {
                if (k == i) continue;
                if (fights[k][j] == 1) {
                    fights[k][i] = 1;
                    fights[i][k] = -1;
                }
                if (fights[k][i] == -1) {
                    fights[j][k] = 1;
                    fights[k][j] = -1;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, new int[][]{
                new int[]{4, 3},
                new int[]{4, 2},
                new int[]{3, 2},
                new int[]{1, 2},
                new int[]{2, 5}
        }));
    }
}

/*


        4, 3
        4, 2
        3, 2    4가 3을 이겼으므로, 4가 2를 이김
        1, 2
        2, 5    1,3,4가 2를 이겼으므로, 1,3,4가 5를 이김

            1   2   3   4   5           1   2   3   4   5
        1   0   1   0   0   0       1   0   1   0   0   1
        2  -1   0  -1   -1  1       2  -1   0  -1  -1   1
        3   0   1   0  -1   0       3   0   1   0  -1   1
        4   0   1   1   0   0       4   0   1   1   0   1
        5   0  -1   0   0   0       5  -1  -1  -1  -1   0

 */