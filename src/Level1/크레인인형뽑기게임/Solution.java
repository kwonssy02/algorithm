// https://programmers.co.kr/learn/courses/30/lessons/64061
package Level1.크레인인형뽑기게임;

import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<moves.length; i++) {
            int move = moves[i]-1;
            for(int j=0; j<board.length; j++) {
                if(board[j][move] == 0) {
                    continue;
                } else if(stack.empty() || stack.peek() != board[j][move]) {
                    stack.push(board[j][move]);
                    board[j][move] = 0;
                    break;
                } else {
                    answer += 2;
                    stack.pop();
                    board[j][move] = 0;
                    break;
                } 
            }
        }
        
        return answer;
    }
}
