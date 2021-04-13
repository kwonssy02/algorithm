// https://programmers.co.kr/learn/courses/30/lessons/12944
package Level1.평균구하기;

class Solution {
    public double solution(int[] arr) {
        double temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp += arr[i];
        }
        return temp / arr.length;
    }
}
