// https://programmers.co.kr/learn/courses/30/lessons/12918
package Level1.문자열다루기기본;

class Solution {
    public boolean solution(String s) {
        
        if(s.length() != 4 && s.length() != 6)
            return false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!('0' <= c && c <= '9')) {
                return false;
            }
        }
        return true;
    }
}
