// https://programmers.co.kr/learn/courses/30/lessons/60059
package Programmers.Level3.자물쇠와열쇠;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {

        int M = key.length;
        int N = lock.length;
        String keyStr = "";
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                keyStr += key[i][j];

        String keyStr90 = "";
        for (int i = 0; i < M; i++)
            for (int j = M - 1; j >= 0; j--)
                keyStr90 += key[j][i];

        String keyStr180 = "";
        for (int i = M - 1; i >= 0; i--)
            for (int j = M - 1; j >= 0; j--)
                keyStr180 += key[i][j];

        String keyStr270 = "";
        for (int i = M - 1; i >= 0; i--)
            for (int j = 0; j < M; j++)
                keyStr270 += key[j][i];

        String lockStr = "";
        for (int i = 0; i < lock.length; i++)
            for (int j = 0; j < lock[i].length; j++)
                lockStr += lock[i][j];

//        System.out.println("keyStr = " + keyStr);
//        System.out.println("keyStr90 = " + keyStr90);
//        System.out.println("keyStr180 = " + keyStr180);
//        System.out.println("keyStr270 = " + keyStr270);
//        System.out.println("lockStr = " + lockStr);

        List<String> list = new ArrayList<>();
        list.addAll(moveKeys(keyStr, M, N));
        list.addAll(moveKeys(keyStr90, M, N));
        list.addAll(moveKeys(keyStr180, M, N));
        list.addAll(moveKeys(keyStr270, M, N));

        for (String str : list) {
            boolean canOpen = true;
            for (int i = 0; i < lockStr.length(); i++) {
                if ((str.charAt(i) ^ lockStr.charAt(i)) == 0) {
                    canOpen = false;
                    break;
                }
            }
            if (canOpen) {
//                System.out.println(str);
                return true;
            }
        }

        return false;
    }

    public List<String> moveKeys(String keyStr, int M, int N) {
        List<String> list = new ArrayList<>();
//        System.out.println("keyStr = " + keyStr + ", M = " + M + ", N = " + N);

        int[][] arrM = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                arrM[i][j] = keyStr.charAt(i * M + j) - '0';
            }
        }

        int[][] arrN;
        for (int i = -M + 1; i <= N - 1; i++) {
            for (int j = -M + 1; j <= N - 1; j++) {
                arrN = new int[N][N];
//                System.out.println("i: " + i + ", j:" + j);
                for (int k = 0; k < M; k++) {
                    for (int l = 0; l < M; l++) {
                        if(i+k < 0 || i+k >= N || j+l < 0 || j+l >= N) continue;
                        arrN[i+k][j+l] = arrM[k][l];
                    }
                }

                String temp = "";
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        temp += arrN[k][l];
                    }
                }
                list.add(temp);
            }
        }

//        for (String s : list) {
//            System.out.println(s);
//        }

        return list;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().solution(new int[][]{
//                new int[]{0, 0, 0},
//                new int[]{1, 0, 0},
//                new int[]{0, 1, 1},
//        }, new int[][]{
//                new int[]{1, 1, 1},
//                new int[]{1, 1, 0},
//                new int[]{1, 0, 1},
//        }));

        System.out.println(new Solution().solution(new int[][]{
                new int[]{0, 0, 0},
                new int[]{1, 0, 0},
                new int[]{0, 1, 1},
        }, new int[][]{
                new int[]{1, 1, 1, 1, 1},
                new int[]{1, 1, 1, 1, 1},
                new int[]{1, 1, 1, 1, 1},
                new int[]{1, 1, 1, 1, 0},
                new int[]{1, 1, 1, 0, 1},
        }));
    }
}

/*

    keyStr90 = 010100100
    keyStr90r1 = 001010010
    keyStr90d1 = 000001010
    keyStr90l1 = 100000000
    lockStr = 111110101


 */