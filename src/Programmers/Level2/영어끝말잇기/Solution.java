// https://programmers.co.kr/learn/courses/30/lessons/12981
package Programmers.Level2.영어끝말잇기;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int[] solution(int n, String[] words) {

        Set<String> set = new HashSet<>();

        char lastChar = words[0].charAt(0);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.charAt(0) != lastChar) {
                return new int[]{i%n+1, i/n+1};
            }
            lastChar = word.charAt(word.length()-1);
            if(!set.add(word)) {
                return new int[]{i%n+1, i/n+1};
            }
        }

        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
    }
}