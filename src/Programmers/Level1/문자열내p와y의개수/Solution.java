// https://programmers.co.kr/learn/courses/30/lessons/12916
package Programmers.Level1.문자열내p와y의개수;

class Solution {

    boolean solution(String s) {

        int pCount = 0;
        int yCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'p':
                case 'P':
                    pCount++;
                    break;
                case 'y':
                case 'Y':
                    yCount++;
                    break;
            }
        }

        return pCount == yCount;
    }
}
