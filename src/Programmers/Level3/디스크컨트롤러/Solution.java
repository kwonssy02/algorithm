// https://programmers.co.kr/learn/courses/30/lessons/42627
package Programmers.Level3.디스크컨트롤러;

import java.util.PriorityQueue;

// PriorityQueue의 iterator, for는 순서를 보장하지 않는다...
// TODO: job 선택 시 Queue를 전체 순회하는 것 개선
class Solution {
    public int solution(int[][] jobs) {
        // 처리 중인 작업이 끝나는 시점까지 요청된 것 중, 작업량 가장 작은 것부터 처리
        // 처리 중인 작업이 끝나는 시점까지 요청이 없으면, 바로 뒤에 먼저 요청된 건 중 작업량 가장 작은 것 처리
        PriorityQueue<C> queue = new PriorityQueue<>();
        for (int i = 0; i < jobs.length; i++) {
            queue.add(new C(jobs[i][0], jobs[i][1]));
        }

        int timeWaited = 0; // 대기시간 총합
        int timePassed = 0; // 총 소요시간
        while (!queue.isEmpty()) {
            C c = null;
            // 개선 필요
            for (C qc : queue) {
                if (qc.start <= timePassed) {
                    if (c == null || qc.time < c.time)
                        c = qc;
                }
            }
            if (c == null) {
                c = queue.poll();
                timePassed = c.start;
            } else
                queue.remove(c);
//            System.out.println(c);

            timePassed += c.time;
            timeWaited += timePassed - c.start;
//            System.out.println("요청시간: " + c.start + ", 소요시간: " + c.time + ", 시작시간: " + (timePassed - c.time) + ", 대기시간: " + (timePassed - c.start));
        }

//        System.out.println("전체 소요시간: " + timePassed);

        return timeWaited / jobs.length;
    }

    class C implements Comparable<C> {
        int start;
        int time;

        public C(int start, int time) {
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(C o) {
            if (start - o.start == 0) {
                return time - o.time;
            }
            return start - o.start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C c = (C) o;
            return start == c.start &&
                    time == c.time;
        }

        @Override
        public String toString() {
            return "C{" +
                    "start=" + start +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                new int[]{0, 3},
                new int[]{1, 9},
                new int[]{2, 6}
        }));
    }
}