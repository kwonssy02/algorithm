// https://programmers.co.kr/learn/courses/30/lessons/12946
package Programmers.Level3.하노이의탑;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 최소 개수 = f(n) = f(n-1) + 1 + f(n-1) = 2f(n-1) + 1
    //      1) 1번째 막대의 n-1개 원판을 2번째 막대로 옮기기
    //      2) 1번째 막대의 n 원판을 3번째 막대로 옮기기
    //      3) 2번째 막대의 n-1개를 3번째 막대로 옮기기
    public void hanoi(int numbers, int source, int stopover, int target, List<Move> moves) {
        if (numbers == 1) {
            moves.add(new Move(source, target));
            return;
        }

        hanoi(numbers - 1, source, target, stopover, moves);
        moves.add(new Move(source, target));
        hanoi(numbers - 1, stopover, source, target, moves);
    }

    public int[][] solution(int n) {
        List<Move> moves = new ArrayList<>();
        hanoi(n, 1, 2, 3, moves);

        int[][] answer = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            answer[i] = new int[]{move.source, move.target};
        }
        return answer;
    }

    class Move {
        int source;
        int target;

        public Move(int source, int target) {
            this.source = source;
            this.target = target;
        }
    }

    public static void main(String[] args) {
        new Solution().solution(3);
    }
}