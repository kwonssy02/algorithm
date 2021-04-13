// https://programmers.co.kr/learn/courses/30/lessons/72410
package Programmers.Level1.신규아이디추천;

import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();

        List<Character> list = new_id.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        for (int i = 0; i < list.size(); i++) {
            Character c = list.get(i);
            if (!(('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.')) {
                list.remove(i--);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Character c = list.get(i);
            if (c == '.') {
                if (i + 1 < list.size() && list.get(i + 1) == '.')
                    list.remove(i--);
            }
        }

        if (list.get(0) == '.')
            list.remove(0);
        if (list.size() > 0 && list.get(list.size() - 1) == '.')
            list.remove(list.size() - 1);

        if (list.size() == 0) {
            list.add('a');
        }

        while (list.size() > 15) {
            list.remove(list.size() - 1);
        }

        while (list.size() > 0 && list.get(list.size() - 1) == '.') {
            list.remove(list.size() - 1);
        }

        while (list.size() < 3) {
            list.add(list.get(list.size() - 1));
        }

        StringBuilder sb = new StringBuilder();
        list.forEach(c -> sb.append(c));

        return sb.toString();
    }
}
