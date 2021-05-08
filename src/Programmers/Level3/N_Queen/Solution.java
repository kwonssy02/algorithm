// https://programmers.co.kr/learn/courses/30/lessons/12952
package Programmers.Level3.N_Queen;

class Solution {
    public int solution(int n) {
        boolean[][] board = new boolean[n][n];

        int answer = dfs(board, 0, n);
        return answer;
    }

    public int dfs(boolean[][] board, int y, int n) {
        if (y == n) {
            return 1;
        }

        int ret = 0;
        for (int x = 0; x < n; x++) {
            if (isValidQueenPosition(board, y, x)) {
                board[y][x] = true;
                ret += dfs(board, y + 1, n);
                board[y][x] = false;
            }
        }

        return ret;
    }

    public boolean isValidQueenPosition(boolean[][] board, int y, int x) {

        for (int i = 0; i < y; i++) {
            if (board[i][x] == true // 같은 x 위치에 퀸이 있거나
                    || (x - (y - i) >= 0 && board[i][x - (y - i)] == true) // 왼쪽 위 대각선에 퀸이 있거나
                    || (x + (y - i) < board.length && board[i][x + (y - i)] == true)) { // 오른쪽 위 대각선에 퀸이 있을 때
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(4));
    }
}