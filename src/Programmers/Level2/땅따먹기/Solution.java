// https://programmers.co.kr/learn/courses/30/lessons/12913
package Programmers.Level2.땅따먹기;

class Solution {

    // DP
    static int solution(int[][] land) {

        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    if (k == j) continue;
                    if (max < land[i - 1][k]) {
                        max = land[i - 1][k];
                    }
                }
                land[i][j] += max;
            }
        }

        return Math.max(land[land.length - 1][0], Math.max(land[land.length - 1][1], Math.max(land[land.length - 1][2], land[land.length - 1][3])));
    }

    // 시간초과
    /*
    static int solution(int[][] land) {

        Queue<C> queue = new LinkedList<>();
        queue.add(new C(0, 0, 0));
        queue.add(new C(0, 1, 0));
        queue.add(new C(0, 2, 0));
        queue.add(new C(0, 3, 0));

        int answer = 0;
        while(!queue.isEmpty()) {
            C poll = queue.poll();
            if(poll.row == land.length) {
                answer = Math.max(answer, poll.sum);
            } else {
                for (int i = 0; i < 4; i++) {
                    if(i == poll.col)
                        continue;
                    queue.add(new C(poll.row+1, i, poll.sum + land[poll.row][i]));
                }
            }
        }

        return answer;
    }
    static class C {
        int row;
        int col;
        int sum;

        public C(int row, int col, int sum) {
            this.row = row;
            this.col = col;
            this.sum = sum;
        }
    }
     */

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{
                        new int[]{1, 2, 3, 5},
                        new int[]{5, 6, 7, 8},
                        new int[]{4, 3, 2, 1}
                }
        ));
    }
}