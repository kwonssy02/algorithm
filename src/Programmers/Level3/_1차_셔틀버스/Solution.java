// https://programmers.co.kr/learn/courses/30/lessons/17678
package Programmers.Level3._1차_셔틀버스;

import java.util.PriorityQueue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 셔틀 운행 횟수 n,
        // 셔틀 운행 간격 t,
        // 한 셔틀에 탈 수 있는 최대 크루 수 m,
        // 크루가 대기열에 도착하는 시각을 모은 배열 timetable
        PriorityQueue<Integer> crews = new PriorityQueue<>();
        for (String s : timetable) {
            int minute = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
            crews.add(minute);
        }

        // 09:00 부터 t분 간격으로 n번 셔틀
        int shuttleTime = 60 * 9;
        int lastCrew = -1;
        int itsConTime = 0;
        for (int i = 0; i < n; i++) { // n번 셔틀
//            System.out.println("suttle: " + shuttleTime);
            int onboard = 0;
            for (int j = 0; j < m; j++) {
                if (!crews.isEmpty() && crews.peek() <= shuttleTime) {
                    Integer crew = crews.poll();
//                    System.out.println("     crew: " + crew);
                    onboard++;
                    lastCrew = crew;
                }
            }

            if (i == n - 1) { // 마지막 셔틀
                if (crews.isEmpty() && onboard < m) { // 모든 크루가 다 타고, 자리가 남았을 경우
                    itsConTime = shuttleTime;
                } else if (crews.isEmpty() && onboard == m) { // 모든 크루가 다 타고, 자리가 꽉 찼을 경우
                    itsConTime = lastCrew - 1;
                } else if (!crews.isEmpty() && onboard == m) { // 모든 크루가 타진 못했지만, 자리가 꽉 찼을 경우
                    itsConTime = lastCrew - 1;
                } else { // 모든 크루가 타진 못했고, 자리도 있을 경우
                    itsConTime = shuttleTime;
                }
            }

            shuttleTime += t;
        }

        return formatTime(itsConTime);
    }

    public String formatTime(int minute) {
        return String.format("%02d:%02d", minute / 60, minute % 60);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(new Solution().solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(new Solution().solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(new Solution().solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(new Solution().solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(new Solution().solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}