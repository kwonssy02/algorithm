// https://programmers.co.kr/learn/courses/30/lessons/12979
package Programmers.Level3.기지국설치;

class Solution {
    public int solution(int n, int[] stations, int w) {

        int answer = 0;
        int coverage = 1 + w * 2;
        if (stations[0] - w > 1) {
            int blocks = stations[0] - w - 1;
            answer += blocks / coverage + (blocks % coverage != 0 ? 1 : 0);
        }
        if (stations[stations.length - 1] + w < n) {
            int blocks = n - (stations[stations.length - 1] + w);
            answer += blocks / coverage + (blocks % coverage != 0 ? 1 : 0);
        }

        for (int i = 0; i < stations.length - 1; i++) {
            int start = stations[i] + w;
            int end = stations[i + 1] - w;
            int blocks = end - start - 1;
            if (blocks > 0) {
                answer += blocks / coverage + (blocks % coverage != 0 ? 1 : 0);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(11, new int[]{4, 11}, 1));
        System.out.println(new Solution().solution(16, new int[]{9}, 2));
    }
}