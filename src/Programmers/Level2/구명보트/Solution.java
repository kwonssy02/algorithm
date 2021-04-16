// https://programmers.co.kr/learn/courses/30/lessons/42885
package Programmers.Level2.구명보트;

import java.util.Arrays;

//TODO: 구명보트 1개를 최대 무게로 싣도록...
class Solution {
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] > limit) { // 최소+최대가 limit 초과일 시 최대만 태우기
                right--;
            } else if (people[left] + people[right] <= limit) { // 최소+최대가 limit 이하일 시 둘 다 태우기
                left++;
                right--;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{50}, 51));
        System.out.println(solution(new int[]{50, 50}, 100));
        System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{160, 150, 140, 60, 50, 40}, 200));
        int[] temp = new int[50000];
        Arrays.fill(temp, 40);
        System.out.println(solution(temp, 240));
//        System.out.println(solution(new int[]{70, 80, 50}, 100));
    }
}