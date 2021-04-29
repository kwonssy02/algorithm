// https://programmers.co.kr/learn/courses/30/lessons/17676
package Programmers.Level3._1차_추석트래픽;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String[] lines) {
        // 각 트래픽의 (끝)~(끝+1초) 구간만 검사
        final Pattern pattern = Pattern.compile("^2016\\-09\\-15 (\\d\\d):(\\d\\d):(\\d\\d)[.](\\d\\d\\d)[ ](\\d(.[\\d]+)?)[s]$");

        Timeline[] timeline = new Timeline[lines.length];

        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            matcher.find();

            int end = ((((Integer.parseInt(matcher.group(1)) * 60) + Integer.parseInt(matcher.group(2))) * 60) + Integer.parseInt(matcher.group(3))) * 1000 + Integer.parseInt(matcher.group(4));
            int start = end - (int) (Double.parseDouble(matcher.group(5)) * 1000) + 1;
            timeline[i] = new Timeline(start, end);
        }

        // 시작시간 기준으로 오름차순 정렬
//        Arrays.sort(timeline, (o1, o2) -> {
//            if (o1.start - o2.start == 0)
//                return o1.end - o2.end;
//            return o1.start - o2.start;
//        });

        // O(n^2) 풀이
        int max = 0;
        for (int i = 0; i < timeline.length; i++) {
            int from = timeline[i].end;
            int to = timeline[i].end + 999;
            int count = 0;
            for (int j = 0; j < timeline.length; j++) {
                if (to < timeline[j].start || from > timeline[j].end)
                    continue;
                count++;
            }
            if (max < count)
                max = count;
        }

        return max;
    }

    class Timeline {
        int start;
        int end;

        public Timeline(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        }));
        System.out.println(new Solution().solution(new String[]{
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        }));
    }
}