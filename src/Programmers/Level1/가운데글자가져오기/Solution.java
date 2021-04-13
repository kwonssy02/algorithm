// https://programmers.co.kr/learn/courses/30/lessons/12903
package Programmers.Level1.가운데글자가져오기;

class Solution {
    public String solution(String s) {

        return s.substring((s.length() - 1) / 2, s.length() / 2 + 1);
    }
}
