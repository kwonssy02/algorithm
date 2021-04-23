// https://programmers.co.kr/learn/courses/30/lessons/72411
package Programmers.Level2.메뉴리뉴얼;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        solution.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5});
        solution.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
    }

    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new TreeMap();
        // 각 Order 별로 tuple 만들기.. (tuple 원소 개수: int[] course의 원소 중 1)
        for (final String order : orders) {
            final char[] menus = order.toCharArray();
            Arrays.sort(menus);

            Set<String> tuples = new TreeSet<>();
            makeTuple("", 0, menus, course, tuples);
            for (final String tuple : tuples) {
                map.put(tuple, map.getOrDefault(tuple, 0) + 1);
            }
        }

        int[] tupleLengthVisited = new int[11]; // 글자 수가 10 이하
        List<C> list = new ArrayList<>();
        // 각 tuple의 length별로 빈도 수 체크. (1은 삭제, 2부터는 가장 큰 것만 필터링)
        for (final Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1)
                list.add(new C(entry.getKey(), entry.getValue()));
        }
        list.sort((o1, o2) -> o2.count - o1.count);
        for (int i = 0; i < list.size(); i++) {
            C c = list.get(i);
            int tupleLength = c.tuple.length();
            if (tupleLengthVisited[tupleLength] == 0) {
                tupleLengthVisited[tupleLength] = c.count;
                continue;
            } else if (tupleLengthVisited[tupleLength] == c.count) {
                continue;
            }
            list.remove(i--);
        }

        // 사전순으로 answer에 넣고 리턴
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).tuple;
        }

        Arrays.sort(answer);
        return answer;
    }

    private void makeTuple(final String tuple, int index, final char[] menus, final int[] course, Set<String> tuples) {
        if (Arrays.binarySearch(course, tuple.length()) >= 0) {
            tuples.add(tuple);
        }

        if (index > menus.length - 1)
            return;

        makeTuple(tuple + menus[index], index + 1, menus, course, tuples);
        makeTuple(tuple, index + 1, menus, course, tuples);
    }

    class C {
        String tuple;
        int count;

        public C(final String tuple, final int count) {
            this.tuple = tuple;
            this.count = count;
        }

        @Override
        public String toString() {
            return "C{" +
                    "tuple='" + tuple + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
