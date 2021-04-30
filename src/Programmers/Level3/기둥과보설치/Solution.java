// https://programmers.co.kr/learn/courses/30/lessons/60061
package Programmers.Level3.기둥과보설치;

import java.util.PriorityQueue;

// 커맨드 하나씩 실행하고 전체 맵 검사하는 것이 멘탈에 좋음 (성능 좋지 않아도)
class Solution {

    static final int NOTHING = Integer.MIN_VALUE;
    static final int STAND = 0;
    static final int BO = 1;

    public int[][] solution(int n, int[][] build_frame) {
        int[][][] map = new int[n+1][n+1][2];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                map[i][j][STAND] = NOTHING;
                map[i][j][BO] = NOTHING;
            }
        }

        for (int[] command : build_frame) {
            int x = command[0];
            int y = command[1];
            int type = command[2];
            int mode = command[3]; // 0은 삭제, 1은 설치

            int before = map[x][y][type];

            if (mode == 1) { // 설치
                map[x][y][type] = 1;
            } else { // 삭제
                map[x][y][type] = NOTHING;
            }

            final boolean test = test(map);
//            System.out.println("test: " + test);
            if (!test) {
                map[x][y][type] = before;
            }
        }

        PriorityQueue<Structure> queue = new PriorityQueue<>();
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (map[i][j][STAND] == 1) {
                    queue.add(new Structure(i, j, STAND));
                }
                if (map[i][j][BO] == 1) {
                    queue.add(new Structure(i, j, BO));
                }

            }
        }

        int[][] answer = new int[queue.size()][3];
        int index = 0;
        while (!queue.isEmpty()) {
            final Structure structure = queue.poll();
            answer[index++] = new int[]{structure.x, structure.y, structure.type};
        }

//        System.out.print("[");
//        for (int i = 0; i < answer.length; i++) {
//            System.out.print(String.format("[%d,%d,%d]", answer[i][0], answer[i][1], answer[i][2]));
//            if (i != answer.length - 1)
//                System.out.print(",");
//        }
//        System.out.println("]");

        return answer;
    }

    class Structure implements Comparable<Structure> {
        int x;
        int y;
        int type;

        public Structure(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Structure o) {
            if (x - o.x == 0) {
                if (y - o.y == 0) {
                    return type - o.type;
                }
                return y - o.y;
            }
            return x - o.x;
        }
    }

    public boolean test(int[][][] map) {

//        for (int y = map.length - 1; y >= 0; y--) {
//            for (int x = 0; x < map.length; x++) {
//                System.out.print(map[x][y]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (map[x][y][STAND] == 1) {
                    if(
                            !(y == 0) // 바닥도 아니고
                            && !(x - 1 >= 0 && (map[x - 1][y][BO] == 1)) // 왼쪽에 보가 없고
                            && !(map[x][y][BO] == 1) // 밑에 보가 없고
                            && !(map[x][y - 1][STAND] == 1) // 아래에 기둥이 없으면
                    ) {
                        return false;
                    }
                }

                if (map[x][y][BO] == 1) {
                    if(
                            !(map[x][y - 1][STAND] == 1) // 아래에 기둥이 없고
                            && !(map[x + 1][y - 1][STAND] == 1) // 오른쪽 아래 기둥이 없고
                            && !((x - 1 >= 0 && (map[x - 1][y][BO] == 1)) && (map[x + 1][y][BO] == 1)) // 왼쪽/오른쪽에 보가 없으면
                    ) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        new Solution().solution(5, new int[][]{
                new int[]{1, 0, 0, 1},
                new int[]{1, 1, 1, 1},
                new int[]{2, 1, 0, 1},
                new int[]{2, 2, 1, 1},
                new int[]{5, 0, 0, 1},
                new int[]{5, 1, 0, 1},
                new int[]{4, 2, 1, 1},
                new int[]{3, 2, 1, 1}
        });

        new Solution().solution(5, new int[][]{
                new int[]{0, 0, 0, 1},
                new int[]{2, 0, 0, 1},
                new int[]{4, 0, 0, 1},
                new int[]{0, 1, 1, 1},
                new int[]{1, 1, 1, 1},
                new int[]{2, 1, 1, 1},
                new int[]{3, 1, 1, 1},
                new int[]{2, 0, 0, 0},
                new int[]{1, 1, 1, 0},
                new int[]{2, 2, 0, 1}
        });
    }
}