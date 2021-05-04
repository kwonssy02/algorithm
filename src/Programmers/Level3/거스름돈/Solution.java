// https://programmers.co.kr/learn/courses/30/lessons/12907
package Programmers.Level3.거스름돈;

class Solution {
    // n원까지 모으는 방법: (n-money[0]) + (n-money[1]) + ...   -->    중복 제거안됨..
    public int solution(int n, int[] money) {
        int[] arr = new int[100001];
        arr[0] = 1;

        for (int i = 0; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                arr[j] += arr[j - money[i]];
            }
        }

        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, new int[]{1, 2, 5}));
    }
}
