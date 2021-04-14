// https://programmers.co.kr/learn/courses/30/lessons/42842
package Programmers.Level2.카펫;

//TODO: 기하 풀이 확인
class Solution {
    public static int[] solution(int brown, int yellow) {
        int xPlusY = (brown+4)/2;
        int xy = yellow + 2*xPlusY - 4;
        int x = (int)Math.ceil(xPlusY/2.0);
        int y = xPlusY/2;

        while(y>0) {
            if(x*y == xy) {
                return new int[]{x, y};
            }
            x++;
            y--;
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(solution(10, 2));
        System.out.println(solution(8, 1));
        System.out.println(solution(24, 24));
    }
}