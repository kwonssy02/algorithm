// https://programmers.co.kr/learn/courses/30/lessons/64062
package Programmers.Level3.징검다리건너기;

import java.util.TreeMap;

class Solution {
    public int solution(int[] stones, int k) {
        // 원소가 k개인 부분배열들 중 최대값이 최소인 것 찾기
        // k개의 리스트를 관리해놓으면서 최대값을 기록하는 형태로 가능..?
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            map.put(stones[i], map.getOrDefault(stones[i], 0) + 1);
        }

        int min = map.lastKey();
        for (int i = k; i < stones.length; i++) {
            map.put(stones[i], map.getOrDefault(stones[i], 0) + 1);
            Integer frontValueCount = map.get(stones[i - k]);
            if(frontValueCount == 1) {
                map.remove(stones[i-k]);
            } else {
                map.put(stones[i-k], frontValueCount-1);
            }
            int max = map.lastKey();

            if (min > max)
                min = max;
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        System.out.println(new Solution().solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1, 2, 1, 1}, 3));
        System.out.println(new Solution().solution(new int[]{1, 1, 1, 1}, 1));
        System.out.println(new Solution().solution(new int[]{1, 1, 1, 1}, 1));
        System.out.println(new Solution().solution(new int[]{5, 5, 5, 5, 3, 3, 3, 3, 3, 5, 5, 5, 5}, 2));
        System.out.println(new Solution().solution(new int[]{5}, 1));
    }
}