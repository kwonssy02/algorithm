// https://programmers.co.kr/learn/courses/30/lessons/12912
package Level1.두정수사이의합;

class Solution {
    public long solution(int a, int b) {
        if (a == b) {
            return a;
        }
        
        long small = Math.min(a, b);
        long big = Math.max(a, b);
    
        return (small+big)*(big-small+1)/2;
    }
}
