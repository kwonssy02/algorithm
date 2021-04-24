// https://programmers.co.kr/learn/courses/30/lessons/1835
package Programmers.Level2.단체사진찍기;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(int n, String[] data) {
        String[] characters = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
        Map<String, List<Integer>> map = new HashMap<>(); // 조건 저장

        for (String d : data) {
            String c1 = d.substring(0, 1);
            String c2 = d.substring(2, 3);
            String op = d.substring(3, 4);
            int distance = Integer.parseInt(d.substring(4));
            String key = (c1.compareTo(c2) < 1 ? c1 + c2 : c2 + c1);
            if (map.get(key) == null) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(0);
                temp.add(1);
                temp.add(2);
                temp.add(3);
                temp.add(4);
                temp.add(5);
                temp.add(6);
                map.put(key, temp);
            }
            List<Integer> availableDistances = map.get(key);

            for (int i = 0; i < availableDistances.size(); i++) {
                if ("=".equals(op)) {
                    if (availableDistances.get(i) != distance)
                        availableDistances.remove(i--);
                } else if ("<".equals(op)) {
                    if (!(availableDistances.get(i) < distance))
                        availableDistances.remove(i--);
                } else if (">".equals(op)) {
                    if (!(availableDistances.get(i) > distance))
                        availableDistances.remove(i--);
                }
            }

            if (availableDistances.size() == 0)
                return 0;
        }

        boolean[] visited = new boolean[characters.length];
        return permutation("", characters, visited, map);
    }

    private int permutation(String p, String[] characters, boolean[] visited, Map<String, List<Integer>> restrictions) {
        for (int i = 0; i < characters.length; i++) {
            for (int j = i + 1; j < characters.length; j++) {
                if (visited[i] && visited[j] && restrictions.get(characters[i] + characters[j]) != null) {
                    int c1Index = p.indexOf(characters[i]);
                    int c2Index = p.indexOf(characters[j]);
                    if (!restrictions.get(characters[i] + characters[j]).contains(Math.abs(c2Index - c1Index) - 1)) {
                        return 0;
                    }
                }
            }
        }

        if (p.length() == characters.length) {
            return 1;
        }

        int answer = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer += permutation(p.concat(characters[i]), characters, visited, restrictions);
                visited[i] = false;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(2, new String[]{"N~F=0", "R~T>2"}));
        System.out.println(solution.solution(2, new String[]{"M~C<2", "C~M>1"}));
        System.out.println(solution.solution(2, new String[]{"N~F=0", "R~T>2"}));
    }
}