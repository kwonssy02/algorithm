// https://programmers.co.kr/learn/courses/30/lessons/49993
package Level2.스킬트리;

class Solution {
    public static int solution(String skill, String[] skill_trees) {

        int answer = 0;
        for (String skill_tree : skill_trees) {
            final String s = skill_tree.replaceAll("[^" + skill + "]", "");
            if(skill.startsWith(s)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }
}
