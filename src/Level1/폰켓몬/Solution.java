// https://programmers.co.kr/learn/courses/30/lessons/1845
package Level1.폰켓몬;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return Math.min(nums.length/2, set.size());
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,1,2,3}));
        System.out.println(solution(new int[]{3,3,3,2,2,4}));
        System.out.println(solution(new int[]{3,3,3,2,2,2}));
    }
}
