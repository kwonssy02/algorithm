// https://programmers.co.kr/learn/courses/30/lessons/12921
package Level1.소수찾기;

class Solution {
    public static int solution(int n) {
        // 소수의 배수는 소수아님
        // p^2 > n 을 처음으로 만족하는 p의 배수까지만 지워가면됨. 

        int[] arr = new int[n+1];
        int p = (int)Math.sqrt(n)+1;

        int countOfPrimeNumbers = n - 1;
        for (int i = 2; i <= p; i++) {
            int m = 2;
            while(i*m <= n) {
                if(arr[i*m] != 1) {
                    arr[i*m] = 1;
                    countOfPrimeNumbers--;
                } 
                m++;
            }
        }
        return countOfPrimeNumbers;
    }
}
