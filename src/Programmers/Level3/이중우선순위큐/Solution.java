// https://programmers.co.kr/learn/courses/30/lessons/42628
package Programmers.Level3.이중우선순위큐;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

    // 이중 우선순위큐
    public int[] solution(String[] operations) {
        PriorityQueue<C> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<C> minHeap = new PriorityQueue<>();

        for (int i = 0; i < operations.length; i++) {
            char op = operations[i].charAt(0);
            int num = Integer.parseInt(operations[i].substring(2));
            if (op == 'I') {
                maxHeap.add(new C(num, i));
                minHeap.add(new C(num, i));
            } else {
                if (maxHeap.size() == 0) continue;
                if (num == 1) {
                    C poll = maxHeap.poll();
                    minHeap.remove(poll);
                } else {
                    C poll = minHeap.poll();
                    maxHeap.remove(poll);
                }
            }
        }

        int[] answer = new int[]{maxHeap.size() == 0 ? 0 : maxHeap.poll().number, minHeap.size() == 0 ? 0 : minHeap.poll().number};
//        System.out.println(answer[0] + " " + answer[1]);
        return answer;
    }

    class C implements Comparable<C> {
        int number;
        int order;

        public C(int number, int order) {
            this.number = number;
            this.order = order;
        }

        @Override
        public int compareTo(C o) {
            return number - o.number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C c = (C) o;
            return number == c.number &&
                    order == c.order;
        }

//        @Override
//        public int hashCode() {
//            return Objects.hash(number, order);
//        }
    }

    // TreeSet 이용. number 중복 시 에러남.
    /*
    public int[] solution(String[] operations) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < operations.length; i++) {
            char op = operations[i].charAt(0);
            int num = Integer.parseInt(operations[i].substring(2));
            if(op == 'I') {
                set.add(num);
            } else {
                if(set.size() == 0) continue;
                if(num == 1) {
                    set.remove(set.last());
                } else {
                    set.remove(set.first());
                }
            }
        }

        int[] answer = new int[]{set.size() == 0 ? 0 : set.last(), set.size() == 0 ? 0 : set.first()};
//        System.out.println(answer[0] + " " + answer[1]);
        return answer;
    }
     */

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[]{"I 16", "D 1"});
        solution.solution(new String[]{"I 7", "I 5", "I -5", "D -1"});
    }
}