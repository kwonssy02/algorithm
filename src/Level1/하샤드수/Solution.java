// https://programmers.co.kr/learn/courses/30/lessons/12947
package Level1.하샤드수;

class Solution {
    public boolean solution(int x) {
        
        int temp = x, sum = 0;
        while(temp > 0) {
            sum += temp%10;
            temp /= 10;
        }
        return x%sum == 0 ? true : false;
    }
}
