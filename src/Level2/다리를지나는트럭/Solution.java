// https://programmers.co.kr/learn/courses/30/lessons/42583
package Level2.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<C> queue = new LinkedList<>();

        for (int i = 0; i < truck_weights.length; i++) {
            queue.add(new C(truck_weights[i], 0));
        }

        int answer = 0;
        while(!queue.isEmpty()) {
            int weight_sum = 0;
            int pollCount = 0;
            for (C c : queue) {
                if(weight_sum + c.truck_weight > weight) {
                    break;
                }

                weight_sum += c.truck_weight;

                c.location++;
                if(c.location == 1) {
                    break;
                }

                if(bridge_length+1 == c.location) {
                    pollCount++;
                    weight_sum -= c.truck_weight;
                }
            }
            answer++;
            while(pollCount-- > 0) {
                queue.poll();
            }
        }

        return answer;
    }

    static class C {
        int truck_weight;
        int location;

        public C(int truck_weight, int location) {
            this.truck_weight = truck_weight;
            this.location = location;
        }

        @Override
        public String toString() {
            return "C{" +
                    "truck_weight=" + truck_weight +
                    ", passed=" + location +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
//        assert solution(2, 10, new int[]{7,4,5,6}) == 8;
    }
}
