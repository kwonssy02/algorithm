// https://programmers.co.kr/learn/courses/30/lessons/72415
package Programmers.Level3.카드짝맞추기;

import java.util.*;

class Solution {
    public int solution(int[][] board, int r, int c) {
        List<Integer> cards = new ArrayList<>();
        Map<Integer, List<Card>> cardPositions = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (cards.indexOf(board[i][j]) < 0) {
                        cards.add(board[i][j]);
                        cardPositions.put(board[i][j], new ArrayList<>());
                        cardPositions.get(board[i][j]).add(new Card(j, i));
                        continue;
                    }
                    cardPositions.get(board[i][j]).add(new Card(j, i));
                }
            }
        }

        List<List<Integer>> cardNumberPermutations = new ArrayList<>();
        boolean[] visited = new boolean[cards.size()];
        cardNumberPermutation(new ArrayList<>(), visited, cards, cardNumberPermutations);
        List<List<Card>> cardPermutations = new ArrayList<>();
        for (List<Integer> cardNumberPermutation : cardNumberPermutations) {
            cardPermutation(0, new ArrayList<>(), cardNumberPermutation, cardPositions, cardPermutations);
        }

        int minMoves = Integer.MAX_VALUE;
        for (List<Card> permutation : cardPermutations) { // 카드 뒤집는 순서
            int[][] tempBoard = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    tempBoard[i][j] = board[i][j];
                }
            }

            int tr = r;
            int tc = c;
            int moves = cards.size() * 2; // Enter 횟수
            for (Card card : permutation) {
                int move = calculateMoves(tc, tr, card.x, card.y, tempBoard);
                moves += move;
                tc = card.x;
                tr = card.y;
                tempBoard[tr][tc] = 0;
            }
            if (minMoves > moves)
                minMoves = moves;
        }

        return minMoves;
    }

    private int calculateMoves(int x, int y, int tx, int ty, int[][] board) {

        Queue<C> q = new LinkedList<>();
        q.add(new C(0, x, y));
        while (!q.isEmpty()) {
            final C poll = q.poll();
            if (poll.x == tx && poll.y == ty)
                return poll.move;

            if (poll.x - 1 >= 0)
                q.add(new C(poll.move + 1, poll.x - 1, poll.y));
            if (poll.x + 1 < board.length)
                q.add(new C(poll.move + 1, poll.x + 1, poll.y));
            if (poll.y - 1 >= 0)
                q.add(new C(poll.move + 1, poll.x, poll.y - 1));
            if (poll.y + 1 < board.length)
                q.add(new C(poll.move + 1, poll.x, poll.y + 1));

            // x 양의 방향 Ctrl 이동
            for (int i = poll.x + 1; i < board.length; i++) {
                if (board[poll.y][i] != 0 || i == board.length - 1) {
                    q.add(new C(poll.move + 1, i, poll.y));
                    break;
                }
            }

            // x 음의 방향 Ctrl 이동
            for (int i = poll.x - 1; i >= 0; i--) {
                if (board[poll.y][i] != 0 || i == 0) {
                    q.add(new C(poll.move + 1, i, poll.y));
                    break;
                }
            }

            // y 양의 방향 Ctrl 이동
            for (int i = poll.y + 1; i < board.length; i++) {
                if (board[i][poll.x] != 0 || i == board.length - 1) {
                    q.add(new C(poll.move + 1, poll.x, i));
                    break;
                }
            }

            // y 음의 방향 Ctrl 이동
            for (int i = poll.y - 1; i >= 0; i--) {
                if (board[i][poll.x] != 0 || i == 0) {
                    q.add(new C(poll.move + 1, poll.x, i));
                    break;
                }
            }
        }

        return 0;
    }

    class C {
        int move;
        int x;
        int y;

        public C(int move, int x, int y) {
            this.move = move;
            this.x = x;
            this.y = y;
        }
    }

    class Card {
        int x;
        int y;

        public Card(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public void cardNumberPermutation(List<Integer> permutation, boolean[] visited, List<Integer> cards, List<List<Integer>> permutations) {
        if (permutation.size() == cards.size()) {
            permutations.add(permutation);
            return;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                ArrayList<Integer> tempPermutation = new ArrayList<>(permutation);
                tempPermutation.add(cards.get(i));
                cardNumberPermutation(tempPermutation, visited, cards, permutations);
                visited[i] = false;
            }
        }
    }

    public void cardPermutation(int index, List<Card> permutation, List<Integer> cards, Map<Integer, List<Card>> positions, List<List<Card>> permutations) {
        if (index == cards.size()) {
            permutations.add(permutation);
            return;
        }

        List<Card> tempPermutation1 = new ArrayList<>(permutation);
        tempPermutation1.add(positions.get(cards.get(index)).get(0));
        tempPermutation1.add(positions.get(cards.get(index)).get(1));
        cardPermutation(index + 1, tempPermutation1, cards, positions, permutations);

        List<Card> tempPermutation2 = new ArrayList<>(permutation);
        tempPermutation2.add(positions.get(cards.get(index)).get(1));
        tempPermutation2.add(positions.get(cards.get(index)).get(0));
        cardPermutation(index + 1, tempPermutation2, cards, positions, permutations);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                new int[]{1, 0, 0, 3},
                new int[]{2, 0, 0, 0},
                new int[]{0, 0, 0, 2},
                new int[]{3, 0, 1, 0},
        }, 1, 0));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{3, 0, 0, 2},
                new int[]{0, 0, 1, 0},
                new int[]{0, 1, 0, 0},
                new int[]{2, 0, 0, 3},
        }, 0, 1));
    }
}

//[Card{x=0, y=1}, Card{x=3, y=2}, Card{x=3, y=0}, Card{x=0, y=3}, Card{x=2, y=3}, Card{x=0, y=0}]