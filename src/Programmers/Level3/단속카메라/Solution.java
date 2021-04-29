// https://programmers.co.kr/learn/courses/30/lessons/42884
package Programmers.Level3.단속카메라;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        // 먼저 빠져나간 차량 순서로 정렬 후, 빠져나간 곳에 카메라를 한대씩 설치하도록..
        Arrays.sort(routes, Comparator.comparingInt(route -> route[1]));
        int lastCameraIndex = -30001;
        int cameras = 0;
        for (int[] route : routes) {
//            System.out.println(route[0] + " " + route[1]);
            if(lastCameraIndex < route[0]) {
                cameras++;
                lastCameraIndex = route[1];
            }
        }

        return cameras;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                new int[]{-20, 15},
                new int[]{-14, -5},
                new int[]{-18, -13},
                new int[]{-5, -3}
        }));
//        System.out.println(new Solution().solution(new int[][]{
//                new int[]{-20, 15},
//                new int[]{16, 17},
//                new int[]{18, 19},
//        }));
    }
}