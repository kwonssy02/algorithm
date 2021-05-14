// https://programmers.co.kr/learn/courses/30/lessons/60063
package Programmers.Level3.블록이동하기;

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        Map<C, Integer> dp = new HashMap<>();
        Queue<C> q = new LinkedList<>();

        q.add(new C(0, 0, 0, 1, 0));
        while (!q.isEmpty()) {
            final C poll = q.poll();
//            System.out.println(poll);
            if (poll.bx == board.length - 1 && poll.by == board.length - 1) {
                return poll.count;
            }

            if (dp.getOrDefault(poll, Integer.MAX_VALUE) <= poll.count)
                continue;

            dp.put(poll, poll.count);

            // up
            if (poll.ay - 1 >= 0 && board[poll.ay - 1][poll.ax] == 0 && board[poll.by - 1][poll.bx] == 0) {
                q.add(new C(poll.count + 1, poll.ax, poll.ay - 1, poll.bx, poll.by - 1));
            }
            // down
            if (poll.by + 1 < board.length && board[poll.ay + 1][poll.ax] == 0 && board[poll.by + 1][poll.bx] == 0) {
                q.add(new C(poll.count + 1, poll.ax, poll.ay + 1, poll.bx, poll.by + 1));
            }
            // left
            if (poll.ax - 1 >= 0 && board[poll.ay][poll.ax - 1] == 0 && board[poll.by][poll.bx - 1] == 0) {
                q.add(new C(poll.count + 1, poll.ax - 1, poll.ay, poll.bx - 1, poll.by));
            }
            // right
            if (poll.bx + 1 < board.length && board[poll.ay][poll.ax + 1] == 0 && board[poll.by][poll.bx + 1] == 0) {
                q.add(new C(poll.count + 1, poll.ax + 1, poll.ay, poll.bx + 1, poll.by));
            }

            if (poll.ay == poll.by) { // ㅡ 형태
                // A 기준
                // turn clockwise
                if (poll.ay + 1 < board.length && board[poll.ay + 1][poll.ax] == 0 && board[poll.by + 1][poll.bx] == 0) {
                    q.add(new C(poll.count + 1, poll.ax, poll.ay, poll.bx - 1, poll.by + 1));
                }
                // turn counter-clockwise
                if (poll.ay - 1 >= 0 && board[poll.ay - 1][poll.ax] == 0 && board[poll.by - 1][poll.bx] == 0) {
                    q.add(new C(poll.count + 1, poll.ax, poll.ay, poll.bx - 1, poll.by - 1));
                }

                // B 기준
                // turn clockwise
                if (poll.ay - 1 >= 0 && board[poll.ay - 1][poll.ax] == 0 && board[poll.by - 1][poll.bx] == 0) {
                    q.add(new C(poll.count + 1, poll.ax + 1, poll.ay - 1, poll.bx, poll.by));
                }
                // turn counter-clockwise
                if (poll.ay + 1 < board.length && board[poll.ay + 1][poll.ax] == 0 && board[poll.by + 1][poll.bx] == 0) {
                    q.add(new C(poll.count + 1, poll.ax + 1, poll.ay + 1, poll.bx, poll.by));
                }
            } else { // | 형태
                // A 기준
                // turn clockwise
                if (poll.ax - 1 >= 0 && board[poll.ay][poll.ax - 1] == 0 && board[poll.by][poll.bx - 1] == 0) {
                    q.add(new C(poll.count + 1, poll.ax, poll.ay, poll.bx - 1, poll.by - 1));
                }
                // turn counter-clockwise
                if (poll.ax + 1 < board.length && board[poll.ay][poll.ax + 1] == 0 && board[poll.by][poll.bx + 1] == 0) {
                    q.add(new C(poll.count + 1, poll.ax, poll.ay, poll.bx + 1, poll.by - 1));
                }

                // B 기준
                // turn clockwise
                if (poll.bx + 1 < board.length && board[poll.by][poll.bx + 1] == 0 && board[poll.ay][poll.ax + 1] == 0) {
                    q.add(new C(poll.count + 1, poll.ax + 1, poll.ay + 1, poll.bx, poll.by));
                }
                // turn counter-clockwise
                if (poll.bx - 1 >= 0 && board[poll.by][poll.bx - 1] == 0 && board[poll.ay][poll.ax - 1] == 0) {
                    q.add(new C(poll.count + 1, poll.ax - 1, poll.ay + 1, poll.bx, poll.by));
                }
            }
        }

        return -1;
    }

    class C {
        int count;
        int ax;
        int ay;
        int bx;
        int by;

        public C(int count, int ax, int ay, int bx, int by) {
            this.count = count;
            if (ax > bx || ay > by) {
                this.ax = bx;
                this.ay = by;
                this.bx = ax;
                this.by = ay;
            } else {
                this.ax = ax;
                this.ay = ay;
                this.bx = bx;
                this.by = by;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C c = (C) o;
            return ax == c.ax && ay == c.ay && bx == c.bx && by == c.by;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ax, ay, bx, by);
        }

        @Override
        public String toString() {
            return "C{" +
                    "count=" + count +
                    ", ax=" + ax +
                    ", ay=" + ay +
                    ", bx=" + bx +
                    ", by=" + by +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0, 1, 1},
                new int[]{0, 0, 0, 1, 0},
                new int[]{0, 1, 0, 1, 1},
                new int[]{1, 1, 0, 0, 1},
                new int[]{0, 0, 0, 0, 0}
        }));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0, 0, 0, 0, 1},
                new int[]{1, 1, 1, 1, 0, 0, 1},
                new int[]{0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 1, 1, 1, 1, 0},
                new int[]{0, 1, 1, 1, 1, 1, 0},
                new int[]{0, 0, 0, 0, 0, 1, 1},
                new int[]{0, 0, 1, 0, 0, 0, 0}
        }));
    }
}