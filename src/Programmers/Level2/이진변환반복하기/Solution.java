// https://programmers.co.kr/learn/courses/30/lessons/70129
package Programmers.Level2.이진변환반복하기;

class Solution {
    public static int[] solution(String s) {
        int count = 0;
        int zeroCount = 0;
        while (!s.equals("1")) {
            count++;
            String zeroRemovedStr = s.replaceAll("0", "");
            zeroCount += s.length() - zeroRemovedStr.length();
            s = binaryString(zeroRemovedStr.length());
        }
        return new int[]{count, zeroCount};
    }

    public static String binaryString(int n) {
        String temp = "";
        while (n > 0) {
            temp = (n % 2) + temp;
            n /= 2;
        }
        return temp;
    }

    public static void main(String[] args) {
        solution("110010101001");
        solution("01110");
        solution("1111111");
    }
}