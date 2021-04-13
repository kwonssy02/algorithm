// https://programmers.co.kr/learn/courses/30/lessons/12934
package Level1.정수제곱근판별;

import java.util.HashMap;

class Solution {
    static HashMap<Long, Long> map = new HashMap<>();
    public static long solution(long n) {
        double sqrt = Math.sqrt(n);
        if(sqrt == (double)((long)sqrt)) {
            return ((long)sqrt+1)*((long)sqrt+1);
        } else {
            return -1;
        }
    }

    
    public static void main(String[] args) {
        System.out.println(solution(121));
        System.out.println(solution(3));
    }
}
