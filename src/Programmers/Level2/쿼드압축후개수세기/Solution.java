// https://programmers.co.kr/learn/courses/30/lessons/68936
package Programmers.Level2.쿼드압축후개수세기;

class Solution {
    public static C quadratic(int[][] arr, int n, int x, int y) {

        boolean isQuadratic = true;
        int temp = arr[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (temp != arr[i][j]) {
                    isQuadratic = false;
                    break;
                }
            }
            if (!isQuadratic)
                break;
        }
        if (isQuadratic) {
            if (temp == 0)
                return new C(1, 0);
            return new C(0, 1);
        }

        return quadratic(arr, n / 2, x, y)
                .add(quadratic(arr, n / 2, x + n / 2, y))
                .add(quadratic(arr, n / 2, x, y + n / 2))
                .add(quadratic(arr, n / 2, x + n / 2, y + n / 2));
    }

    public static int[] solution(int[][] arr) {
        final C quadratic = quadratic(arr, arr.length, 0, 0);
        int[] answer = {quadratic.zero, quadratic.one};
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{
                new int[]{1, 1, 0, 0},
                new int[]{1, 0, 0, 0},
                new int[]{1, 0, 0, 1},
                new int[]{1, 1, 1, 1}
        });
        solution(new int[][]{
                new int[]{1, 1, 1, 1, 1, 1, 1, 1},
                new int[]{0, 1, 1, 1, 1, 1, 1, 1},
                new int[]{0, 0, 0, 0, 1, 1, 1, 1},
                new int[]{0, 1, 0, 0, 1, 1, 1, 1},
                new int[]{0, 0, 0, 0, 0, 0, 1, 1},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1},
                new int[]{0, 0, 0, 0, 1, 0, 0, 1},
                new int[]{0, 0, 0, 0, 1, 1, 1, 1}
        });
    }

    static class C {
        int zero;
        int one;

        public C(final int zero, final int one) {
            this.zero = zero;
            this.one = one;
        }

        C add(C other) {
            return new C(this.zero + other.zero, this.one + other.one);
        }
    }
}
