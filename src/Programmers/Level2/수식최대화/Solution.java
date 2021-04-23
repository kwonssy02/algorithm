// https://programmers.co.kr/learn/courses/30/lessons/67257
package Programmers.Level2.수식최대화;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public long solution(String expression) {
        Pattern pattern = Pattern.compile("([+*-]?)([0-9]+)");
        final Matcher m = pattern.matcher(expression);
        List<C> list = new ArrayList<>();
        Set<Character> operands = new HashSet<>();
        while (m.find()) {
            list.add(new C(
                    Integer.parseInt(m.group(2)),
                    "".equals(m.group(1)) ? ' ' : m.group(1).charAt(0))
            );

            if (!"".equals(m.group(1)))
                operands.add(m.group(1).charAt(0));
        }

        char[] operandArr = new char[operands.size()];
        int index = 0;
        for (final Character operand : operands)
            operandArr[index++] = operand;

        boolean[] visited = new boolean[operandArr.length];
        List<String> operandPermutations = new ArrayList<>();
        permutation("", operandArr, visited, operandPermutations);

        long max = -1;
        for (final String permutation : operandPermutations) {
            // deep copy 해야함.
            List<C> tempList = new ArrayList<>();
            for (final C c : list) {
                tempList.add(c.copy());
            }

            for (int i = 0; i < permutation.length(); i++) {
                char operand = permutation.charAt(i);
                for (int j = 1; j < tempList.size(); j++) {
                    if (tempList.get(j).operand == operand) {
                        switch (operand) {
                            case '-':
                                tempList.get(j - 1).number -= tempList.get(j).number;
                                break;
                            case '+':
                                tempList.get(j - 1).number += tempList.get(j).number;
                                break;
                            case '*':
                                tempList.get(j - 1).number *= tempList.get(j).number;
                                break;
                        }
                        tempList.remove(j--);
                    }
                }
            }
//            System.out.println(permutation);
//            System.out.println(Math.abs(tempList.get(0).number));
            if (max < Math.abs(tempList.get(0).number))
                max = Math.abs(tempList.get(0).number);
        }

        return max;
    }

    public void permutation(String str, char[] operands, boolean[] visited, List<String> permutations) {
        if (str.length() == operands.length) {
            permutations.add(str);
            return;
        }

        for (int i = 0; i < operands.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(str + operands[i], operands, visited, permutations);
                visited[i] = false;
            }
        }
    }

    class C {
        long number;
        char operand;

        public C(final long number, final char operand) {
            this.number = number;
            this.operand = operand;
        }

        public C copy() {
            return new C(number, operand);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution("100-200*300-500+20"));
        System.out.println(solution.solution("50*6-3*2"));
    }
}
