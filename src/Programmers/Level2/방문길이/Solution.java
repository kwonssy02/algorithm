// https://programmers.co.kr/learn/courses/30/lessons/49994
package Programmers.Level2.방문길이;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(String dirs) {
        // (x, y) -> (x+-1, y), (x, y) -> (x, y+-1) 지나갔을 때 기록 필요
        int x = 0, y = 0;
        int answer = 0;
        Set<String> set = new HashSet<>();
        for (char c : dirs.toCharArray()) {
            int _x = x, _y = y;
            switch (c) {
                case 'U':
                    if(y+1 > 5) continue;
                    _y++;
                    break;
                case 'D':
                    if(y-1 < -5) continue;
                    _y--;
                    break;
                case 'R':
                    if(x+1 > 5) continue;
                    _x++;
                    break;
                case 'L':
                    if(x-1 < -5) continue;
                    _x--;
                    break;
            }
            if(set.add("" + (x+_x) + (y+_y)))
                answer++;

            x = _x;
            y = _y;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }
}