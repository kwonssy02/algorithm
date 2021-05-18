// https://programmers.co.kr/learn/courses/30/lessons/1836
package Programmers.Level3.리틀프렌즈사천성;

import java.util.*;

class Solution {
    public String solution(int m, int n, String[] board) {
        char[][] b = new char[m][n];
        Map<Character, List<Block>> blocksPairs = new TreeMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                b[i][j] = board[i].charAt(j);
                if (b[i][j] == '.' || b[i][j] == '*') continue;
                if (blocksPairs.get(b[i][j]) == null) {
                    List<Block> list = new ArrayList<>();
                    list.add(new Block(i, j));
                    blocksPairs.put(b[i][j], list);
                } else {
                    blocksPairs.get(b[i][j]).add(new Block(i, j));
                }
            }
        }

        int pairs = blocksPairs.size();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pairs; i++) {
            Character removedBlock = null;
            final Iterator<Map.Entry<Character, List<Block>>> iter = blocksPairs.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry<Character, List<Block>> next = iter.next();
                char block = next.getKey();
                final List<Block> blockPair = next.getValue();
                if (isRemovable(blockPair.get(0).m, blockPair.get(0).n, blockPair.get(1).m, blockPair.get(1).n, b)) {
                    removedBlock = block;
                    sb.append(block);
                    b[blockPair.get(0).m][blockPair.get(0).n] = '.';
                    b[blockPair.get(1).m][blockPair.get(1).n] = '.';
                    break;
                }
            }
            if (removedBlock == null)
                return "IMPOSSIBLE";
            blocksPairs.remove(removedBlock);
        }

        return sb.toString();
    }

    class Block {
        int m;
        int n;

        public Block(int m, int n) {
            this.m = m;
            this.n = n;
        }
    }

    public boolean isRemovable(int am, int an, int bm, int bn, char[][] b) {
        if (an > bn) {
            int temp = an;
            an = bn;
            bn = temp;

            temp = am;
            am = bm;
            bm = temp;
        }

        if (am == bm) {
            for (int i = an + 1; i <= bn - 1; i++) {
                if (b[am][i] != '.')
                    return false;
            }
        } else if (an == bn) {
            if (am > bm) {
                int temp = am;
                am = bm;
                bm = temp;
            }
            for (int i = am + 1; i <= bm - 1; i++) {
                if (b[i][an] != '.')
                    return false;
            }
        } else if (am < bm) {
            boolean up = true, down = true, left = true, right = true;
            for (int i = an + 1; i <= bn; i++) {
                if (b[am][i] != '.') {
                    up = false;
                    break;
                }
            }

            for (int i = an; i <= bn - 1; i++) {
                if (b[bm][i] != '.') {
                    down = false;
                    break;
                }
            }

            for (int i = am + 1; i <= bm; i++) {
                if (b[i][an] != '.') {
                    left = false;
                    break;
                }
            }

            for (int i = am; i <= bm - 1; i++) {
                if (b[i][bn] != '.') {
                    right = false;
                    break;
                }
            }

            if (!(up && right) && !(left && down)) return false;
        } else {
            boolean up = true, down = true, left = true, right = true;
            for (int i = an; i <= bn - 1; i++) {
                if (b[bm][i] != '.') {
                    up = false;
                    break;
                }
            }

            for (int i = an + 1; i <= bn; i++) {
                if (b[am][i] != '.') {
                    down = false;
                    break;
                }
            }

            for (int i = bm; i <= am - 1; i++) {
                if (b[i][an] != '.') {
                    left = false;
                    break;
                }
            }

            for (int i = bm + 1; i <= am; i++) {
                if (b[i][bn] != '.') {
                    right = false;
                    break;
                }
            }

            if (!(up && left) && !(right && down)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        System.out.println(new Solution().solution(2, 4, new String[]{"NRYN", "ARYA"}));
        System.out.println(new Solution().solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
        System.out.println(new Solution().solution(2, 2, new String[]{"AB", "BA"}));
    }
}