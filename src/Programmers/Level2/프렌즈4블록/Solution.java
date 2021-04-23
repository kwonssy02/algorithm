// https://programmers.co.kr/learn/courses/30/lessons/17679
package Programmers.Level2.프렌즈4블록;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(2, 2, new String[]{"CC", "CC"}));
        System.out.println(solution.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
        System.out.println(solution.solution(6, 6, new String[]{"TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT"}));
        System.out.println(solution.solution(4, 4, new String[]{"AAAA", "AMMA", "AMMA", "AAAA"}));


    }

    public int solution(int m, int n, String[] board) {
        List<List<Character>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = board.length - 1; i >= 0; i--) {
            final char[] chars = board[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                list.get(j).add(chars[j]);
            }
        }

        int totalRemovedCount = 0;
        while (true) {
            TreeSet<C> toRemove = new TreeSet<>();

            for (int i = 0; i < list.size() - 1; i++) {
                final List<Character> line1 = list.get(i);
                final List<Character> line2 = list.get(i + 1);
                for (int j = 0; j <= line1.size() - 2; j++) {
                    final Character c = line1.get(j);
                    if (j > line2.size() - 2)
                        break;
                    if (c == line1.get(j + 1)
                            && c == line2.get(j)
                            && c == line2.get(j + 1)) {
                        toRemove.add(new C(i, j));
                        toRemove.add(new C(i, j + 1));
                        toRemove.add(new C(i + 1, j));
                        toRemove.add(new C(i + 1, j + 1));
                    }
                }
            }

            if (toRemove.isEmpty())
                break;

            final Iterator<C> iter = toRemove.iterator();
            while (iter.hasNext()) {
                final C next = iter.next();
//                System.out.println(next);
                list.get(next.x).remove(next.y);
            }
//            System.out.println("round ended");

            totalRemovedCount += toRemove.size();
        }

        return totalRemovedCount;
    }

    class C implements Comparable<C> {
        int x;
        int y;

        public C(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(final C o) {
            if (x == o.x)
                return o.y - y;
            return o.x - x;
        }
    }
}
