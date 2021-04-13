// https://programmers.co.kr/learn/courses/30/lessons/12925
package Level1.문자열을정수로바꾸기;

class Solution {
    public static int solution(String s) {
        
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        System.out.println(solution("1234"));
        System.out.println(solution("-1234"));
    }
}
