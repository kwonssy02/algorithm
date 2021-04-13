// https://programmers.co.kr/learn/courses/30/lessons/12926
package Level1.시저암호;

class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') sb.append(' ');
            else if ('a' <= c && c <= 'z') {
                sb.append((char)('a' + (c+n-'a')%26));
            } else {
                sb.append((char)('A' + (c+n-'A')%26));
            }
                
        }
        return sb.toString();
    }
}
