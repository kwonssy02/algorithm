// https://programmers.co.kr/learn/courses/30/lessons/12904
package Programmers.Level3.가장긴팰린드롬;

class Solution {
    public int solution(String s) {
        char[] chars = s.toCharArray();

        for (int i = chars.length; i >= 2; i--) { // 문자열 길이
//            System.out.println("i = " + i);
            for (int j = 0; j <= chars.length - i; j++) { // substring의 시작 index
//                System.out.println("    j = " + j);
                boolean palindrome = true;
                for (int k = 0; k <= i / 2; k++) {
//                    System.out.println("      a = " + (j + k) + " " + chars[j + k]);
//                    System.out.println("      b = " + (j + i - 1 - k) + " " + chars[j + i - 1 - k]);
//                    System.out.println();
                    if (chars[j + k] != chars[j + i - 1 - k]) {
                        palindrome = false;
                        break;
                    }
                }
                if (palindrome) {
                    return i;
                }
            }
//            System.out.println();
        }

        return 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("iufeldsmciadsmmiudfui"));
        System.out.println(new Solution().solution("abacde"));
        System.out.println(new Solution().solution("aaaaab"));
        System.out.println(new Solution().solution("a"));
        System.out.println(new Solution().solution("aa"));
        System.out.println(new Solution().solution("abcdefg"));
    }
}