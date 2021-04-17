// https://programmers.co.kr/learn/courses/30/lessons/42888
package Programmers.Level2.오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    static public String[] solution(String[] record) {

        // uid의 닉네임을 따로 저장
        // action 따로 저장하여
        // 최종으로 메시지 생성

        Map<String, String> nicknames = new HashMap<>();
        List<C> list = new ArrayList<>();

        for (String s : record) {
            String[] split = s.split(" ");

            if("Enter".equals(split[0])) {
                nicknames.put(split[1], split[2]);
                list.add(new C(0, split[1]));
            } else if ("Leave".equals(split[0])) {
                list.add(new C(1, split[1]));
            } else if("Change".equals(split[0])) {
                nicknames.put(split[1], split[2]);
            }
        }

        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            final C c = list.get(i);
            answer[i] = nicknames.get(c.uid) + (c.action == 0 ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }

        return answer;
    }

    static class C {
        int action; // 0 Enter, 1 Leave
        String uid;

        public C(int action, String uid) {
            this.action = action;
            this.uid = uid;
        }
    }

    public static void main(String[] args) {
        solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
    }
}