// https://programmers.co.kr/learn/courses/30/lessons/43163
package Programmers.Level3.단어변환;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {

        Queue<C> queue = new LinkedList<>();

        for (int i = 0; i < words.length; i++) {
            if (isChangeable(begin, words[i])) {
                boolean[] visited = new boolean[words.length];
                visited[i] = true;
                queue.add(new C(words[i], visited, 1));
            }
        }

        while (!queue.isEmpty()) {
            final C c = queue.poll();
            if (target.equals(c.current))
                return c.step;
            for (int i = 0; i < words.length; i++) {
                if (c.current.equals(words[i])) continue;
                if (isChangeable(c.current, words[i]) && !c.visited[i]) {
                    boolean[] visited = Arrays.copyOf(c.visited, c.visited.length);
                    visited[i] = true;
                    queue.add(new C(words[i], visited, c.step + 1));
                }
            }
        }

        return 0;
    }

    class C {
        String current;
        boolean[] visited;
        int step;

        public C(String current, boolean[] visited, int step) {
            this.current = current;
            this.visited = visited;
            this.step = step;
        }
    }

    public boolean isChangeable(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i) && ++count > 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

}