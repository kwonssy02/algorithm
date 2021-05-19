// https://programmers.co.kr/learn/courses/30/lessons/1830
package Programmers.Level3.브라이언의고민;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public String solution(String sentence) {
        StringBuilder sb = new StringBuilder();
        try {
            Set<Character> usedChars = new HashSet<>();
            parse(0, sentence, usedChars, sb);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "invalid";
        }
        return sb.toString();
    }

    public void parse(int index, String sentence, Set<Character> usedChars, StringBuilder result) {
        System.out.println("index = " + index + ", sentence = " + sentence + ", usedChars = " + usedChars + ", result = " + result);
        if (index >= sentence.length())
            return;

        char firstChar = sentence.charAt(index);

        if ('a' <= firstChar && firstChar <= 'z') { // 규칙 2 (규칙1도 적용될 수 있음)
            char secondRuleChar = firstChar;
            if (usedChars.contains(secondRuleChar)) throw new RuntimeException("used chars1");
            usedChars.add(secondRuleChar);

            int lastIndex = sentence.indexOf(secondRuleChar, index + 1);
            if (lastIndex < 0) throw new RuntimeException("err1");

            Character firstRuleChar = null;
            int firstRuleCharIndex = -1;
            StringBuilder sb = new StringBuilder();
            for (int i = index + 1; i <= lastIndex - 1; i++) {
                char ch = sentence.charAt(i);
                System.out.println(ch);
                if (('a' <= ch && ch <= 'z') && secondRuleChar == ch) throw new RuntimeException("err2"); // 규칙2 캐릭터 사용됨
                if (('a' <= ch && ch <= 'z') && firstRuleChar == null) { // 규칙1 캐릭터 발견
                    if (usedChars.contains(ch)) throw new RuntimeException("used chars2");
                    usedChars.add(ch);

                    firstRuleChar = ch;
                    firstRuleCharIndex = i;
                    continue;
                }
                if (('a' <= ch && ch <= 'z') && firstRuleChar != ch)
                    throw new RuntimeException("err3"); // 규칙1 캐릭터 mismatch
                if (('a' <= ch && ch <= 'z')) {
                    if (firstRuleCharIndex + 2 != i) throw new RuntimeException("err4"); // 규칙1 캐릭터 배열 에러
                    firstRuleCharIndex = i;
                    continue;
                }
                sb.append(sentence.charAt(i));
            }
            if (firstRuleCharIndex != -1 && firstRuleCharIndex != lastIndex - 2)
                throw new RuntimeException("err5"); // 규칙1 캐릭터 위치 오류

            if(result.length() != 0) result.append(" ");
            result.append(sb);
            parse(lastIndex + 1, sentence, usedChars, result);
        } else { // 규칙 1 or 무규칙
            int firstSpecialCharIndex = -1;
            for (int i = index + 1; i < sentence.length(); i++) {
                char ch = sentence.charAt(i);
                if ('a' <= ch && ch <= 'z') {
                    firstSpecialCharIndex = i;
                    break;
                }
            }
            if (firstSpecialCharIndex == -1) firstSpecialCharIndex = sentence.length() + 1; // 특수문자 못찾았다면 문장 마지막 인덱스로 변경
            if (firstSpecialCharIndex >= index + 2) { // 무규칙
                if(result.length() != 0) result.append(" ");
                result.append(sentence, index, firstSpecialCharIndex - 1);
                parse(firstSpecialCharIndex - 1, sentence, usedChars, result);
                return;
            } else { // 규칙1
//                StringBuilder sb = new StringBuilder();
//                Character firstRuleChar = null;
//                int firstRuleCharIndex = -1;
//                for (int i = index; i < sentence.length(); i++) {
//                    char ch = sentence.charAt(i);
//                    if(('a' <= ch && ch <= 'z') && firstRuleChar == null) {
//                        if (usedChars.contains(ch)) throw new RuntimeException("used chars3");
//                        usedChars.add(ch);
//
//                        firstRuleChar = ch;
//                        firstRuleCharIndex = i;
//                        continue;
//                    }
//                    if(('a' <= ch && ch <= 'z') && firstRuleChar != ch) {
//
//
//                    }
//                    if(('a' <= ch && ch <= 'z')) {
//                        if (firstRuleCharIndex + 2 != i) throw new RuntimeException("err4"); // 규칙1 캐릭터 배열 에러
//                        firstRuleCharIndex = i;
//                        continue;
//                    }
//                    sb.append(ch);
//                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("bHaEaLaLaObQQQZpZpZ"));
//        System.out.println(new Solution().solution("HaEaLaLaObWORLDb"));
//        System.out.println(new Solution().solution("SpIpGpOpNpGJqOqA"));
//        System.out.println(new Solution().solution("AxAxAxAoBoBoB"));
    }
}

/*
    aAa
    AaAaA
 */