// https://programmers.co.kr/learn/courses/30/lessons/12905
package Programmers.Level2.가장큰정사각형찾기;

class Solution {

    // DP
    public static int solution(int[][] board) {
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        answer = Math.max(answer, 1);
                    } else {
                        board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1;
                        if (answer < board[i][j])
                            answer = board[i][j];
                    }
                }
            }
        }
        return answer * answer;
    }

    // 시간초과
    /*
    public static int solution(int [][]board)
    {
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 1) {
                    int maxLength = Math.min(board.length-i, board[i].length-j);
                    if(answer >= maxLength) {
                        break;
                    }
                    for (int m = maxLength; m >= answer+1; m--) {
                        boolean flag = true;
                        for (int k = i+m-1; k >= i; k--) {
                            for (int l = j+m-1; l >= j; l--) {
                                if(board[k][l] == 0){
                                    m = Math.min(k-i, l-j)+1;
                                    flag = false;
                                    break;
                                }
                            }
                            if(!flag)
                                break;
                        }
                        if(flag) {
                            answer = m;
                            break;
                        }
                    }
                }
            }
        }

        return answer*answer;
    }
     */

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{new int[]{1}}));
        System.out.println(solution(new int[][]{new int[]{0, 1, 1, 1}, new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1}, new int[]{0, 0, 1, 0}}));
        System.out.println(solution(new int[][]{new int[]{0, 0, 1, 1}, new int[]{1, 1, 1, 1}}));
        System.out.println(solution(
                new int[][]{
                        new int[]{0, 0, 0, 0},
                        new int[]{0, 0, 0, 0},
                        new int[]{0, 0, 0, 0},
                        new int[]{0, 0, 0, 0},
                        new int[]{0, 0, 0, 1}
                }
        ));
    }
}