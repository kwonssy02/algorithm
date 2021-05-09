// https://programmers.co.kr/learn/courses/30/lessons/12971
package Programmers.Level3.스티커모으기2;

class Solution {

    public int solution(int sticker[]) {
        if (sticker.length == 1) return sticker[0];

        int[][] dp = new int[sticker.length][2];

        // 1번째 미선택
        dp[0][0] = 0;
        for (int i = 1; i < sticker.length; i++) {
            // i번째 자리를 선택하지 않았을 때의 최대값 -> Max((i-1 선택),(i-1 미선택))
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // i번째 자리를 선택했을 때의 최대값 -> (i-1 미선택) + i번째
            dp[i][1] = dp[i - 1][0] + sticker[i];
        }
        int firstSkipped = Math.max(dp[sticker.length - 1][0], dp[sticker.length - 1][1]);

        // 1번째 선택
        dp = new int[sticker.length][2];
        dp[1][0] = sticker[0];
        for (int i = 2; i < sticker.length - 1; i++) {
            // i번째 자리를 선택하지 않았을 때의 최대값 -> Max((i-1 선택),(i-1 미선택))
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // i번째 자리를 선택했을 때의 최대값 -> (i-1 미선택) + i번째
            dp[i][1] = dp[i - 1][0] + sticker[i];
        }
        int firstSelected = Math.max(dp[sticker.length - 2][0], dp[sticker.length - 2][1]);

        return Math.max(firstSkipped, firstSelected);
    }

    // 시간초과
    /*
    public int solution(int sticker[]) {

        return Math.max(
                dfs(1, 0, sticker),
                dfs(2, sticker[0], Arrays.copyOf(sticker, sticker.length - 1))
        );
    }

    public int dfs(int index, int sum, int[] sticker) {
        if (index >= sticker.length)
            return sum;

        int skip = dfs(index + 1, sum, sticker);
        int selected = dfs(index + 2, sum + sticker[index], sticker);

        return Math.max(skip, selected);
    }
     */

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10}));
        System.out.println(new Solution().solution(new int[]{1, 3, 2, 5, 4}));
        System.out.println(new Solution().solution(new int[]{1}));
    }
}