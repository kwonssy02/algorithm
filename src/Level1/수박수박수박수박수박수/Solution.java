// https://programmers.co.kr/learn/courses/30/lessons/12922
package Level1.수박수박수박수박수박수;

class Solution {
    public static String solution(int n) {
        return new String(new char [n/2+1]).replace("\0", "수박").substring(0,n);
    }
    
    public static void main(String[] args) {
        solution(1);
        solution(2);
        solution(5);
        solution(100);
    }
}
