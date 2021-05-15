// https://programmers.co.kr/learn/courses/30/lessons/72414
package Programmers.Level3.광고삽입;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Sliding window
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        final Pattern timePattern = Pattern.compile("(\\d\\d):(\\d\\d):(\\d\\d)");
        final Pattern logPattern = Pattern.compile("(\\d\\d):(\\d\\d):(\\d\\d)-(\\d\\d):(\\d\\d):(\\d\\d)");

        final Matcher playTimeMatcher = timePattern.matcher(play_time);
        playTimeMatcher.find();
        int playTimeSec = Integer.parseInt(playTimeMatcher.group(1)) * 3600 + Integer.parseInt(playTimeMatcher.group(2)) * 60 + Integer.parseInt(playTimeMatcher.group(3));
//        System.out.println("playTimeSec = " + playTimeSec);

        final Matcher advTimeMatcher = timePattern.matcher(adv_time);
        advTimeMatcher.find();
        int advTimeSec = Integer.parseInt(advTimeMatcher.group(1)) * 3600 + Integer.parseInt(advTimeMatcher.group(2)) * 60 + Integer.parseInt(advTimeMatcher.group(3));
//        System.out.println("advTimeSec = " + advTimeSec);

        int[] timeArr = new int[playTimeSec + 1];
        for (String log : logs) {
            final Matcher logMatcher = logPattern.matcher(log);
            logMatcher.find();
            int startSec = Integer.parseInt(logMatcher.group(1)) * 3600 + Integer.parseInt(logMatcher.group(2)) * 60 + Integer.parseInt(logMatcher.group(3));
            int endSec = Integer.parseInt(logMatcher.group(4)) * 3600 + Integer.parseInt(logMatcher.group(5)) * 60 + Integer.parseInt(logMatcher.group(6));
            for (int i = startSec; i < endSec; i++) {
                timeArr[i]++;
            }
        }

        long duration = 0;
        for (int i = 0; i < advTimeSec; i++) {
            duration += timeArr[i];
        }
        long maxDuration = duration;
        int start = 0;
        for (int i = 1; i <= playTimeSec - advTimeSec; i++) {
            duration -= timeArr[i - 1];
            duration += timeArr[i + advTimeSec - 1];
            if (duration > maxDuration) {
                start = i;
                maxDuration = duration;
            }
        }
//        System.out.println("duration = " + duration);

        return "" + start / 36000 + start % 36000 / 3600 + ":" + start % 3600 / 600 + start % 600 / 60 + ":" + start % 60 / 10 + start % 10;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
        System.out.println(new Solution().solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
        System.out.println(new Solution().solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
    }
}