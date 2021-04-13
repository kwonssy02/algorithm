// https://programmers.co.kr/learn/courses/30/lessons/43165
package Level2.타겟넘버;

class Solution {
    public static int solution(int[] numbers, int target) {

        int answer = dfs(numbers, target, -1, 0);
        return answer;
    }

    public static int dfs(int[] num, int target, int index, int acc) {
//        System.out.println("dfs(" + num + "," + target + "," + index + "," + acc + ")");
        if(index == num.length-1) {
            return target == acc ? 1 : 0;
        }

        return dfs(num, target, index+1, acc+num[index+1]) + dfs(num, target, index+1, acc-num[index+1]);

    }

    public static void main(String[] args) {
        assert solution(new int[]{1, 1, 1, 1, 1}, 3) == 5;
    }
}
