// https://programmers.co.kr/learn/courses/30/lessons/12945
package Programmers.Level2.피보나치수;

class Solution {
    static int[] arr = new int[100001];

    public static int solution(int n) {

        int answer = fibonacci(n);
        return answer;
    }

    public static int fibonacci(int n) {
        if (arr[n] != 0) return arr[n];
        if (n == 0 || n == 1) return n;
        int fibonacci = fibonacci(n - 1) % 1234567 + fibonacci(n - 2) % 1234567;
        arr[n] = fibonacci % 1234567;
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
        System.out.println(solution(5));
    }
}
