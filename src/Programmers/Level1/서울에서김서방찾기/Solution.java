// https://programmers.co.kr/learn/courses/30/lessons/12919
package Programmers.Level1.서울에서김서방찾기;

class Solution {
    public String solution(String[] seoul) {

        String target = "Kim";
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals(target))
                return "김서방은 " + i + "에 있다";
        }
        return null;
    }
}
