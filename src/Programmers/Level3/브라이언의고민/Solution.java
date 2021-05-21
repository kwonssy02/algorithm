// https://programmers.co.kr/learn/courses/30/lessons/1830
package Programmers.Level3.브라이언의고민;

import org.assertj.core.api.Assertions;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public String solution(String sentence) {
        if (sentence.equals("") || sentence.indexOf(' ') >= 0)
            return "invalid";

        StringBuilder sb = new StringBuilder();
        try {
            Set<Character> usedChars = new HashSet<>();
            parse(0, sentence, usedChars, sb);
        } catch (Exception e) {
//            e.printStackTrace();
            return "invalid";
        }
        return sb.toString();
    }

    public void parse(int index, String sentence, Set<Character> usedChars, StringBuilder result) {
//        System.out.println("index = " + index + ", sentence = " + sentence + ", usedChars = " + usedChars + ", result = " + result);
        if (index >= sentence.length())
            return;

        char firstChar = sentence.charAt(index);

        if ('a' <= firstChar && firstChar <= 'z') { // 규칙 2 (규칙1도 적용될 수 있음)
            char secondRuleChar = firstChar;
            if (usedChars.contains(secondRuleChar)) throw new RuntimeException("used chars1");
            usedChars.add(secondRuleChar);

            int lastIndex = sentence.indexOf(secondRuleChar, index + 1);
            if (lastIndex == index + 1) throw new RuntimeException("no char");
            if (lastIndex < 0) throw new RuntimeException("err1");

            Character firstRuleChar = null;
            int firstRuleCharIndex = -1;
            StringBuilder sb = new StringBuilder();
            for (int i = index + 1; i <= lastIndex - 1; i++) {
                char ch = sentence.charAt(i);
//                System.out.println(ch);
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

            if (result.length() != 0) result.append(" ");
            result.append(sb);
            parse(lastIndex + 1, sentence, usedChars, result);
        } else { // 규칙 1 or 무규칙
            int firstSpecialCharIndex = -1;
            Character firstSpecialChar = null;
            for (int i = index + 1; i < sentence.length(); i++) {
                char ch = sentence.charAt(i);
                if ('a' <= ch && ch <= 'z') {
                    firstSpecialCharIndex = i;
                    firstSpecialChar = ch;
                    break;
                }
            }
            if (firstSpecialCharIndex == -1) firstSpecialCharIndex = sentence.length() + 1; // 특수문자 못찾았다면 문장 마지막 인덱스로 변경
            if (firstSpecialCharIndex >= index + 2) { // 무규칙
                if (firstSpecialChar != null && sentence.indexOf(firstSpecialChar, firstSpecialCharIndex + 1) > firstSpecialCharIndex + 2) { // 규칙2 발견
                    if (result.length() != 0) result.append(" ");
                    result.append(sentence, index, firstSpecialCharIndex);
                    parse(firstSpecialCharIndex, sentence, usedChars, result);
                    return;
                }
                // 규칙1
                if (result.length() != 0) result.append(" ");
                result.append(sentence, index, firstSpecialCharIndex - 1);
                parse(firstSpecialCharIndex - 1, sentence, usedChars, result);
                return;
            } else { // 규칙1
                // 마지막 규칙1 캐릭터 index+1까지만 계산 후 뒤를 재귀
                StringBuilder sb = new StringBuilder();
                Character firstRuleChar = null;
                int firstRuleCharIndex = -1;
                for (int i = index; i < sentence.length(); i++) {
                    char ch = sentence.charAt(i);
                    if (('a' <= ch && ch <= 'z') && firstRuleChar == null) {
                        if (sentence.indexOf(ch, i + 1) > i + 2) { // 또다른 firstRuleChar가 엉뚱한 곳에서 발견될 경우, 다른 규칙
                            if (result.length() != 0) result.append(" ");
                            result.append(sentence.charAt(index));
                            parse(index + 1, sentence, usedChars, result);
                            return;
                        }
                        if (usedChars.contains(ch)) throw new RuntimeException("used chars3");
                        usedChars.add(ch);

                        firstRuleChar = ch;
                        firstRuleCharIndex = i;
                        continue;
                    }
                    if (('a' <= ch && ch <= 'z') && firstRuleChar != ch) break;
                    if (('a' <= ch && ch <= 'z')) {
                        if (firstRuleCharIndex + 2 != i) throw new RuntimeException("err4"); // 규칙1 캐릭터 배열 에러
                        firstRuleCharIndex = i;
                        if (firstRuleCharIndex == sentence.length() - 1) {
                            usedChars.remove(sentence.charAt(firstRuleCharIndex));

                            if (result.length() != 0) result.append(" ");
                            result.append(sentence.charAt(index));
                            parse(index + 1, sentence, usedChars, result);
                            return;
                        }
                        continue;
                    }
                }

                for (int i = index; i <= firstRuleCharIndex + 1; i += 2) {
                    sb.append(sentence.charAt(i));
                }

                if (result.length() != 0) result.append(" ");
                result.append(sb);
                parse(firstRuleCharIndex + 2, sentence, usedChars, result);
            }
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

        Assertions.assertThat(solution.solution("HaEaLaLaObWORLDb")).isEqualTo("HELLO WORLD"); // 규칙 1, 2 한번씩
        Assertions.assertThat(solution.solution("aHELLOaWbObRbLbD")).isEqualTo("HELLO WORLD"); //규칙 2, 1 한번씩
        Assertions.assertThat(solution.solution("aHELLOabWORLDb")).isEqualTo("HELLO WORLD"); // 규칙 2, 2
        Assertions.assertThat(solution.solution("HaEaLaLaOWbObRbLbD")).isEqualTo("HELLO WORLD"); // 규칙 1, 1
        Assertions.assertThat(solution.solution("SpIpGpOpNpGJqOqA")).isEqualTo("SIGONG JOA");  // 규칙 1, 규칙x, 규칙2, 규칙x
        Assertions.assertThat(solution.solution("HaEaLaLaO")).isEqualTo("HELLO"); // 규칙1
        Assertions.assertThat(solution.solution("aHELLOa")).isEqualTo("HELLO"); // 규칙 2
        Assertions.assertThat(solution.solution("HELLOaWORLDa")).isEqualTo("HELLO WORLD");//규칙 적용 하지 않은 문자 + 규칙 2
        Assertions.assertThat(solution.solution("HELLOWaOaRaLaD")).isEqualTo("HELLO WORLD"); //규칙 적용 하지 않은 문자 + 규칙 1
        Assertions.assertThat(solution.solution("aHELLOaWORLD")).isEqualTo("HELLO WORLD"); //규칙 2 + 규칙 적용 하지 않은 문자
        Assertions.assertThat(solution.solution("HaEaLaLaOWORLD")).isEqualTo("HELLO WORLD"); //규칙 1 + 규칙 적용 하지 않은 문자
        Assertions.assertThat(solution.solution("aHbEbLbLbOacWdOdRdLdDc")).isEqualTo("HELLO WORLD"); // 규칙 1,2 +  규칙 1, 2
        Assertions.assertThat(solution.solution("aAbAa")).isEqualTo("AA"); //규칙 1, 2 짧은 글자
        Assertions.assertThat(solution.solution("aGbWbFbDakGnWnLk")).isEqualTo("GWFD GWL");
        Assertions.assertThat(solution.solution("aGbWbFbDakGnWnLkAAA")).isEqualTo("GWFD GWL AAA");

        Assertions.assertThat(solution.solution("")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("aaA")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("aa")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("TxTxTxbAb")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("TxTxTxbAb")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("bTxTxTaTxTbkABaCDk")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("xAaAbAaAx")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("baHELLOabWORLD")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("AbAaAbAaCa")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("aCaCa")).isEqualTo("invalid"); //INVALID
        Assertions.assertThat(solution.solution("AaAaAcA")).isEqualTo("invalid"); //INVALID
    }
}

/*
    aAa
    AaAaA
 */