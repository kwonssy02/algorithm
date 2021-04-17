// https://programmers.co.kr/learn/courses/30/lessons/12911
package Programmers.Level2.다음큰숫자;

//TODO: Bit 이용하여 풀기
class Solution {
    public static int solution(int n) {

        int[] binary = new int[20];
        int index = 19;
        while (n > 0) {
            binary[index] = n % 2;
            n /= 2;
            index--;
        }

        int countOfOne = 0;
        for (int i = binary.length - 1; i > 0; i--) {
            if (binary[i] == 1) {
                countOfOne++;
                if (binary[i - 1] == 0) {
                    binary[i - 1] = 1;
                    countOfOne--;
                    for (int j = i; j < binary.length - countOfOne; j++) {
                        binary[j] = 0;
                    }
                    for (int j = binary.length - countOfOne; j < binary.length; j++) {
                        binary[j] = 1;
                    }
                    break;
                }
            }
        }

        int answer = 0;
        int multiply = 1;
        for (int i = binary.length - 1; i >= 0; i--) {
            answer += multiply * binary[i];
            multiply *= 2;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(78));
        System.out.println(solution(15));
    }
}