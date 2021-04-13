// https://programmers.co.kr/learn/courses/30/lessons/12930
package Programmers.Level1.이상한문자만들기;

class Solution {

    public static String solution(String s) {

        StringBuilder sb = new StringBuilder();
        int ni = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                ni = -1;
                sb.append(' ');
            } else {
                ni++;
                if (ni % 2 == 0) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
