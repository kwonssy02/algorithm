// https://programmers.co.kr/learn/courses/30/lessons/77886
package Programmers.Level3._110옮기기;

import org.assertj.core.api.Assertions;

import java.util.Stack;

class Solution {
    public String[] solution(String[] s) {
        // 110을 뺄 수 있을 때까지 뺀 후,
        // 111 있으면 앞에 배치
        // 111 없으면 11 앞에 배치
        // 11 없을 시, 마지막이 0이면 0뒤에 붙이고 1이면 1 앞에 붙이기
        StringBuilder sb110 = new StringBuilder("110");
        for (int i = 0; i < 19; i++) {
            sb110.append(sb110);
        }

        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            final Stack<Character> stack1 = new Stack<>();
            final Stack<Character> stack2 = new Stack<>();
            for (int j = 0; j < s[i].length(); j++)
                stack1.add(s[i].charAt(j));

            int countOf110 = 0;
            while (!stack1.empty()) {
                final Character pop = stack1.pop();
                if ((!stack2.empty() && stack2.peek() == '0') && pop == '1' && (!stack1.empty() && stack1.peek() == '1')) {
                    stack1.pop();
                    stack2.pop();
                    countOf110++;
                    continue;
                }
                if ((stack2.size() >= 2 && stack2.get(stack2.size() - 2) == '0' && stack2.peek() == '1') && pop == '1') {
                    stack2.pop();
                    stack2.pop();
                    countOf110++;
                    continue;
                }
                stack2.add(pop);
            }

            StringBuilder sb = new StringBuilder();
            while (!stack2.empty())
                sb.append(stack2.pop());

            if (sb.length() == 0) {
                answer[i] = sb110.substring(0, countOf110 * 3);
                continue;
            }

            int indexOf111 = sb.indexOf("111");
            int indexOf11 = sb.indexOf("11");
            if (indexOf111 >= 0) {
                sb.insert(indexOf111, sb110, 0, countOf110 * 3);
            } else if (indexOf11 >= 0) {
                sb.insert(indexOf11, sb110, 0, countOf110 * 3);
            } else if (sb.charAt(sb.length() - 1) == '0') {
                sb.append(sb110, 0, countOf110 * 3);
            } else {
                sb.insert(sb.length() - 1, sb110, 0, countOf110 * 3);
            }
//            System.out.println(sb.toString());
            answer[i] = sb.toString();
        }

        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        Assertions.assertThat(solution.solution(new String[]{"1110", "100111100", "0111111010"})).containsExactly("1101", "100110110", "0110110111");
        Assertions.assertThat(solution.solution(new String[]{"1", "0"})).containsExactly("1", "0");
        Assertions.assertThat(solution.solution(new String[]{"1011110", "01110", "101101111010"})).containsExactly("1011011", "01101", "101101101101");
        Assertions.assertThat(solution.solution(new String[]{"110", "10"})).containsExactly("110", "10");
    }
}
