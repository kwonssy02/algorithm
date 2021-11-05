// https://leetcode.com/problems/two-sum/
package LeetCode._1_Two_Sum;

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++) {
            int number = nums[i];
            if(!map.containsKey(target-number)) {
                map.put(number, i);
                continue;
            }
            return new int[]{map.get(target-number) , i};
        }

        return null;
    }
}