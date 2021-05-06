// https://programmers.co.kr/learn/courses/30/lessons/68646
package Programmers.Level3.풍선터트리기;

class Solution {
    public int solution(int[] a) {

        // 항상 더 큰 풍선을 터트려아하고, 더 작은 풍선을 터트리는 것은 1회만 가능
        // 1번째 풍선은 2~n번째 풍선을 모두 정리 후, 대소 관계없이 남길 수 있음.
        // 2번째 풍선은 1번째 풍선보다 작을 경우, 남길 수 있음.
        //                         클 경우, 3~n번째보다 항상 작아야 남길 수 있음.

        int[] forward = new int[a.length]; // 앞에서부터 누적으로 최소값 기록
        int[] backward = new int[a.length]; // 뒤에서부터 누적으로 최소값 기록
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (temp > a[i])
                temp = a[i];
            forward[i] = temp;
        }

        temp = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            if (temp > a[i])
                temp = a[i];
            backward[i] = temp;
        }

        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] < forward[i - 1]) { // 이전 풍선들의 최소값보다 작을 경우
                answer++;
            } else if (a[i] > forward[i - 1]) { // 이전 풍선들의 최소값보다 클 경우
                if (i == a.length - 1 || a[i] < backward[i + 1]) { // 이후의 풍선들의 최소값보다 작을 경우
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{9, -1, -5}));
        System.out.println(new Solution().solution(new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}));
    }
}