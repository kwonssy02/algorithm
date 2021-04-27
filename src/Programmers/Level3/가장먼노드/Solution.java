// https://programmers.co.kr/learn/courses/30/lessons/49189
package Programmers.Level3.가장먼노드;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    // 인접행렬 vs 인접리스트
    public int solution(int n, int[][] edge) {

        // 인접리스트로 구현.
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int source = e[0];
            int target = e[1];

            edges.get(source).add(target);
            edges.get(target).add(source);
        }

        int[] visited = new int[n+1];
        visited[1] = 1;
        Queue<C> q = new LinkedList<>();

        q.add(new C(1, 0));

        while (!q.isEmpty()) {
            final C poll = q.poll();

            for (Integer nextSource : edges.get(poll.source)) {
                if (visited[nextSource] > 0)
                    continue;
                visited[nextSource] = poll.distance + 1;
                q.add(new C(nextSource, poll.distance + 1));
            }
        }

        int maxValue = 0;
        int maxCount = 0;
        for (int i = 2; i < visited.length; i++) {
            if(maxValue < visited[i]) {
                maxValue = visited[i];
                maxCount = 1;
            } else if(maxValue == visited[i])
                maxCount++;
        }

        return maxCount;
    }

    class C {
        int source;
        int distance;

        public C(int source, int distance) {
            this.source = source;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "C{" +
                    "source=" + source +
                    ", distance=" + distance +
                    '}';
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(6, new int[][]{
                new int[]{3, 6},
                new int[]{4, 3},
                new int[]{3, 2},
                new int[]{1, 3},
                new int[]{1, 2},
                new int[]{2, 4},
                new int[]{5, 2},
        }));
        System.out.println(solution.solution(11, new int[][]{
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 4},
                new int[]{2, 5},
                new int[]{3, 5},
                new int[]{3, 6},
                new int[]{4, 8},
                new int[]{4, 9},
                new int[]{5, 9},
                new int[]{5, 10},
                new int[]{6, 10},
                new int[]{6, 11}
        }));

    }
}