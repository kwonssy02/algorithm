// https://programmers.co.kr/learn/courses/30/lessons/42893
package Programmers.Level3.매칭점수;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 정규식 \S 숙지하기
class Solution {
    public int solution(String word, String[] pages) {

        word = word.toLowerCase();
        final Pattern pageUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        final Pattern exUrlPattern = Pattern.compile("<a href=\"(\\S*)\"");

        // 각 page url 추출
        // 각 page의 기본점수 계산
        // 각 페이지마다 외부링크의 링크점수 더해주기
        String[] pageUrls = new String[pages.length];
        double[] scores = new double[pages.length];
        Map<String, Double> linkScores = new HashMap<>();
        for (int i = 0; i < pages.length; i++) {
            final Matcher pageUrlMatcher = pageUrlPattern.matcher(pages[i]);
            pageUrlMatcher.find();
            pageUrls[i] = pageUrlMatcher.group(1);

            String pageLowercase = pages[i].toLowerCase();
            int wordCount = 0;
            int fromIndex = 0;
            while (true) {
                int foundIndex = pageLowercase.indexOf(word, fromIndex);
                if (foundIndex < 0) break;
                if (!isAlphabet(pageLowercase.charAt(foundIndex - 1)) && !isAlphabet(pageLowercase.charAt(foundIndex + word.length())))
                    wordCount++;
                fromIndex = foundIndex + word.length();
            }

            scores[i] = wordCount;
//            System.out.println("wordCount = " + wordCount);

            final Matcher exUrlMatcher = exUrlPattern.matcher(pages[i]);
            List<String> exUrls = new ArrayList<>();
            while (exUrlMatcher.find()) {
                exUrls.add(exUrlMatcher.group(1));
            }
            for (String exUrl : exUrls) {
                linkScores.put(exUrl, linkScores.getOrDefault(exUrl, 0.0) + scores[i] / exUrls.size());
            }
        }

        for (int i = 0; i < pages.length; i++) {
            scores[i] += linkScores.getOrDefault(pageUrls[i], 0.0);
        }

//        for (int i = 0; i < pages.length; i++) {
//            System.out.print(scores[i] + " ");
//        }
//        System.out.println();

        int maxIndex = 0;
        double maxScore = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (maxScore < scores[i]) {
                maxScore = scores[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public boolean isAlphabet(char c) {
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'))
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("blind", new String[]{
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        }));

        System.out.println(new Solution().solution("Muzi", new String[]{
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        }));
    }
}