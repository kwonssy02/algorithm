// https://programmers.co.kr/learn/courses/30/lessons/12940
package Level1.최대공약수와최소공배수;

class Solution {
    public int[] solution(int n, int m) {
        if(n > m) {
            int temp = n;
            n = m;
            m = temp;
        }

        int gcd = gcd(n, m);

        return new int[]{gcd, n*m/gcd};
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }

}
