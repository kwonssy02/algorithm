// https://programmers.co.kr/learn/courses/30/lessons/67259
package Programmers.Level3.경주로건설;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] board) {
        int[][] dp = new int[board.length][board.length];

        Queue<C> q = new LinkedList<>();
        if (board[1][0] == 0)
            q.add(new C(1, 0, 0, 0, 1));
        if (board[0][1] == 0)
            q.add(new C(0, 1, 0, 0, 1));

        while (!q.isEmpty()) {
            C poll = q.poll();
//            System.out.println(poll);

            if (dp[poll.x][poll.y] != 0 && dp[poll.x][poll.y] < poll.price)
                continue;

            dp[poll.x][poll.y] = poll.price;

            if (0 <= poll.x - 1 && board[poll.x - 1][poll.y] == 0)
                q.add(new C(poll.x - 1, poll.y, poll.x, poll.y, poll.price + (poll.x - poll.bx != 0 ? 1 : 6)));
            if (poll.x + 1 < board.length && board[poll.x + 1][poll.y] == 0)
                q.add(new C(poll.x + 1, poll.y, poll.x, poll.y, poll.price + (poll.x - poll.bx != 0 ? 1 : 6)));
            if (0 <= poll.y - 1 && board[poll.x][poll.y - 1] == 0)
                q.add(new C(poll.x, poll.y - 1, poll.x, poll.y, poll.price + (poll.y - poll.by != 0 ? 1 : 6)));
            if (poll.y + 1 < board.length && board[poll.x][poll.y + 1] == 0)
                q.add(new C(poll.x, poll.y + 1, poll.x, poll.y, poll.price + (poll.y - poll.by != 0 ? 1 : 6)));
        }

        return dp[board.length - 1][board.length - 1] * 100;
    }

    class C {
        int x, y;
        int bx, by;
        int price;

        public C(int x, int y, int bx, int by, int price) {
            this.x = x;
            this.y = y;
            this.bx = bx;
            this.by = by;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 0, 0},
                new int[]{0, 0, 0}
        }));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0, 0, 0, 0, 0, 1},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 1, 0, 0},
                new int[]{0, 0, 0, 0, 1, 0, 0, 0},
                new int[]{0, 0, 0, 1, 0, 0, 0, 1},
                new int[]{0, 0, 1, 0, 0, 0, 1, 0},
                new int[]{0, 1, 0, 0, 0, 1, 0, 0},
                new int[]{1, 0, 0, 0, 0, 0, 0, 0},
        }));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 1, 0},
                new int[]{0, 0, 0, 0},
                new int[]{0, 1, 0, 1},
                new int[]{1, 0, 0, 0}
        }));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0, 0, 0, 0},
                new int[]{0, 1, 1, 1, 1, 0},
                new int[]{0, 0, 1, 0, 0, 0},
                new int[]{1, 0, 0, 1, 0, 1},
                new int[]{0, 1, 0, 0, 0, 1},
                new int[]{0, 0, 0, 0, 0, 0}
        }));

    }
}