// https://programmers.co.kr/learn/courses/30/lessons/12977
package Programmers.Level1.소수만들기;

class Solution {
    public int solution(int[] nums) {
        int[] arr = new int[2997 + 1];
        int p = 55;

        for (int i = 2; i <= p; i++) {
            int m = 2;
            while (i * m <= 2997) {
                if (arr[i * m] != 1) {
                    arr[i * m] = 1;
                }
                m++;
            }
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (arr[nums[i] + nums[j] + nums[k]] == 0)
                        count++;
                }
            }
        }

        return count;
    }
}
