// https://programmers.co.kr/learn/courses/30/lessons/72412
package Programmers.Level2.순위검색;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(
                new String[]{
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"
                }
                , new String[]{
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"
                }
        );
    }

    // Level2 끝판왕
    // Point 1: info 순회하며 모든 경우의 수에 대해 indexing
    // Point 2: Map Value를 query 순회 돌기 전에 모두 Sorting 해놓기
    // Point 3: Score 검색 시 binary Search (이분 탐색)
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        String[] languages = new String[]{"-", "java", "python", "cpp"};
        String[] parts = new String[]{"-", "backend", "frontend"};
        String[] careers = new String[]{"-", "junior", "senior"};
        String[] soulFoods = new String[]{"-", "chicken", "pizza"};
        for (String language : languages) {
            for (String part : parts) {
                for (String career : careers) {
                    for (String soulFood : soulFoods) {
                        map.put(language + part + career + soulFood, new ArrayList<>());
                    }
                }
            }
        }

        for (int i = 0; i < info.length; i++) {
            final String[] split = info[i].split(" ");
            String l = split[0];
            String p = split[1];
            String c = split[2];
            String s = split[3];
            int score = Integer.parseInt(split[4]);

            for (String language : Arrays.asList("-", l)) {
                for (String part : Arrays.asList("-", p)) {
                    for (String career : Arrays.asList("-", c)) {
                        for (String soulFood : Arrays.asList("-", s)) {
                            map.get(language + part + career + soulFood).add(score);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        int[] answer = new int[query.length];
        for (int qi = 0; qi < query.length; qi++) {
            String q = query[qi];
            final String[] split = q.split(" and ");
            String language = split[0];
            String part = split[1];
            String career = split[2];
            String soulFood = split[3].split(" ")[0];
            int score = Integer.parseInt(split[3].split(" ")[1]);

            final List<Integer> list = map.get(language + part + career + soulFood);
            answer[qi] = list.size() - binarySearch(list, score);
        }

        return answer;
    }

    // 시간 초과
    /*
    public int[] solution(String[] info, String[] query) {
        // 언어 직군 경력 소울푸드 점수
        // 각 항목별로 인덱싱 어떻게...?
        //      언어/직군/경력/소울푸드 -> HashMap
        //      점수 -> TreeMap

        Map<String, List<Integer>> languageMap = new HashMap<>();
        languageMap.put("java", new ArrayList<>());
        languageMap.put("python", new ArrayList<>());
        languageMap.put("cpp", new ArrayList<>());

        Map<String, List<Integer>> partMap = new HashMap<>();
        partMap.put("backend", new ArrayList<>());
        partMap.put("frontend", new ArrayList<>());

        Map<String, List<Integer>> careerMap = new HashMap<>();
        careerMap.put("junior", new ArrayList<>());
        careerMap.put("senior", new ArrayList<>());

        Map<String, List<Integer>> soulFoodMap = new HashMap<>();
        soulFoodMap.put("chicken", new ArrayList<>());
        soulFoodMap.put("pizza", new ArrayList<>());

        List<Score> scoreList = new ArrayList<>(info.length);

        for (int i = 0; i < info.length; i++) {
            final String[] split = info[i].split(" ");
            languageMap.get(split[0]).add(i);
            partMap.get(split[1]).add(i);
            careerMap.get(split[2]).add(i);
            soulFoodMap.get(split[3]).add(i);
            scoreList.add(new Score(Integer.parseInt(split[4]), i));
        }

        Collections.sort(scoreList);

        int[] answer = new int[query.length];
        for (int qi = 0; qi < query.length; qi++) {
            String q = query[qi];
            final String[] split = q.split(" and ");
            String language = split[0];
            String part = split[1];
            String career = split[2];
            String soulFood = split[3].split(" ")[0];
            int score = Integer.parseInt(split[3].split(" ")[1]);

//            System.out.println("language = " + language);
//            System.out.println("part = " + part);
//            System.out.println("career = " + career);
//            System.out.println("soulFood = " + soulFood);
//            System.out.println("score = " + score);

            List<Integer> result = new ArrayList<>();
            int si = binarySearch(scoreList, score);
            for (int j = si; j < scoreList.size(); j++) {
                result.add(scoreList.get(j).index);
            }

            if (!"-".equals(language))
                result.retainAll(languageMap.get(language));
            if (!"-".equals(part))
                result.retainAll(partMap.get(part));
            if (!"-".equals(career))
                result.retainAll(careerMap.get(career));
            if (!"-".equals(soulFood))
                result.retainAll(soulFoodMap.get(soulFood));

//            System.out.println("result.size() = " + result.size());
            answer[qi] = result.size();
        }


        return answer;
    }

    public int binarySearch(List<Score> list, int targetScore) {
        int start = 0;
        int end = list.size() - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (list.get(mid).score < targetScore) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return start;
    }
    
    class Score implements Comparable<Score> {
        int score;
        int index;

        public Score(int score, int index) {
            this.score = score;
            this.index = index;
        }

        @Override
        public int compareTo(Score o) {
            return score - o.score;
        }

        @Override
        public String toString() {
            return "Score{" +
                    "score=" + score +
                    ", index=" + index +
                    '}';
        }
    }
     */

    private int binarySearch(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}