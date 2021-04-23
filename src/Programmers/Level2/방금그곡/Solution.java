// https://programmers.co.kr/learn/courses/30/lessons/17683
package Programmers.Level2.방금그곡;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: C# 등의 음을 한 문자로 치환 시 복잡도 감소
//TODO: Sort 하지 않고도 풀이 가능
class Solution {

    Pattern pattern = Pattern.compile("[ABCDEFG][#]?");

    public String solution(String m, String[] musicinfos) {

        final Matcher m1 = pattern.matcher(m);
        StringBuilder sb1 = new StringBuilder();
        while (m1.find()) {
            sb1.append(m1.group() + "-");
        }
        m = sb1.toString();

        List<C> list = new ArrayList<>();
        int index = 0;
        for (final String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");
            int startHour = Integer.parseInt(split[0].substring(0, 2));
            int startMin = Integer.parseInt(split[0].substring(3, 5));
            int endHour = Integer.parseInt(split[1].substring(0, 2));
            int endMin = Integer.parseInt(split[1].substring(3, 5));
            int duration = (endHour - startHour) * 60 + (endMin - startMin);
            String name = split[2];
            List<String> notes = new ArrayList<>();
            Matcher matcher = pattern.matcher(split[3]);
            while (matcher.find()) {
                notes.add(matcher.group());
            }

            StringBuilder sb = new StringBuilder();
            int temp = duration;
            int i = 0;
            while (temp-- > 0) {
                sb.append(notes.get(i++ % notes.size()) + "-");
            }
            String played = sb.toString();
            if (played.contains(m))
                list.add(new C(name, duration, index));

            index++;
        }

        if (list.size() == 0)
            return "(None)";

        list.sort((o1, o2) -> {
            if (o2.duration - o1.duration == 0) {
                return o1.order - o2.order;
            }
            return o2.duration - o1.duration;
        });

        return list.get(0).name;
    }

    class C {
        String name;
        int duration;
        int order;

        public C(final String name, final int duration, final int order) {
            this.name = name;
            this.duration = duration;
            this.order = order;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(solution.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(solution.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}
