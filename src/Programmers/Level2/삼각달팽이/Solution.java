package Programmers.Level2.삼각달팽이;

class Solution {

    public static int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];

        int index = 0;
        int num = 0;
        int base = -1;
        for (int i = n; i >= 1; i -= 3) {
            while (answer[index] != 0) {
                index++;
            }
            num++;
            answer[index] = num;

            base += 2;
            for (int j = 0; j < i - 1; j++) {
                num++;
                index += j + base;
                answer[index] = num;
            }

            for (int j = 0; j < i - 1; j++) {
                num++;
                index++;
                answer[index] = num;
            }

            for (int j = 0; j < i - 2; j++) {
                num++;
                index -= n - j - base / 2;
                answer[index] = num;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        solution(4);
        solution(5);
        solution(6);
    }
}