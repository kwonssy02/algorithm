// https://programmers.co.kr/learn/courses/30/lessons/42885
package Programmers.Level2.구명보트;

import java.util.Arrays;

//TODO: 구명보트 1개를 최대 무게로 싣도록...
class Solution {
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;
        for (int i = 0; i < people.length; i++) {
            if(people[i] > limit/2) {
                answer += people.length-i;
                break;
            }

            if(i+1 < people.length && people[i] + people[i+1] <= limit) {
                i++;
                answer++;
                continue;
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