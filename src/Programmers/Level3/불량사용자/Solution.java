// https://programmers.co.kr/learn/courses/30/lessons/64064
package Programmers.Level3.불량사용자;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {

        List<List<String>> candidates = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            candidates.add(new ArrayList<>());
            for (int j = 0; j < user_id.length; j++) {
                if (banned_id[i].length() != user_id[j].length()) continue;
                boolean match = true;
                for (int k = 0; k < banned_id[i].length(); k++) {
                    if (banned_id[i].charAt(k) == '*') continue;
                    if (banned_id[i].charAt(k) != user_id[j].charAt(k)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    candidates.get(i).add(user_id[j]);
                }
            }
        }

        Set<String> bannedList = new HashSet<>();
        dfs(0, new HashSet<>(), candidates, bannedList);

        return bannedList.size();
    }

    private void dfs(int index, Set<String> list, List<List<String>> candidates, Set<String> bannedList) {
        if (index == candidates.size()) {
            String s = list.stream().sorted().reduce((acc, str) -> acc + "/" + str).get();
            bannedList.add(s);
            return;
        }
        for (int i = 0; i < candidates.get(index).size(); i++) {
            if (list.contains(candidates.get(index).get(i))) continue;
            list.add(candidates.get(index).get(i));
            dfs(index + 1, list, candidates, bannedList);
            list.remove(candidates.get(index).get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(new Solution().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(new Solution().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }
}