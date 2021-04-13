// https://programmers.co.kr/learn/courses/30/lessons/12933
package Level1.정수내림차순으로배치하기;

class Solution {
    String temp = "";
    public long solution(long n) {
        
        Long.toString(n)
            .chars()
            .sorted()
            .forEach(c -> temp = Character.toString((char)c) + temp);

        return Long.parseLong(temp);
    }
}
