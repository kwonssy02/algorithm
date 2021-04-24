// https://programmers.co.kr/learn/courses/30/lessons/42628
package Programmers.Level3.이중우선순위큐;

import java.util.TreeSet;

//TODO: 이중우선순위큐를 사용해서 풀기
class Solution {
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

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[]{"I 16", "D 1"});
        solution.solution(new String[]{"I 7","I 5","I -5","D -1"});
    }
}