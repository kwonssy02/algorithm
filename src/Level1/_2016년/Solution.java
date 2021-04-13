// https://programmers.co.kr/learn/courses/30/lessons/12901
package Level1._2016년;

class Solution {

    static String[] days = {"THU", "FRI","SAT","SUN","MON","TUE","WED"};
    static int[] daysInMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static String solution(int a, int b) {
        int dayCount = 0;
        for(int i=0; i<=a-1; i++) {
            dayCount += daysInMonth[i];
        }
        dayCount += b;

        
        return days[dayCount%7];
    }

}
