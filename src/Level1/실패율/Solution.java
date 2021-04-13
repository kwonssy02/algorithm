// https://programmers.co.kr/learn/courses/30/lessons/42889
package Level1.실패율;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public static int[] solution(int N, int[] stages) {
        // 실패율: 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
        // n 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수: count(stages[i] == n)
        // n 스테이지에 도달한 플레이어 수: (stages[i] == 1) + ... + (stages[i] == n)

        StageInfo[] stageInfos = new StageInfo[N+1];
        for (int i = 1; i <= N; i++) {
            stageInfos[i] = new StageInfo(i);
        }

        for (int stage: stages) {
            if(stage != N+1)
                stageInfos[stage].struggling++;
            for (int i = 1; i <= Math.min(stage, N); i++) {
                stageInfos[i].reached++;
            }
        }

        Arrays.sort(stageInfos, 1, N+1,
                Comparator.comparing(StageInfo::getFailRate).reversed()
                    .thenComparing(StageInfo::getStage)
        );

        int[] answer = new int[N];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = stageInfos[i+1].getStage();
        }
        return answer;
    }

    static class StageInfo {
        int stage;
        int struggling = 0;
        int reached = 0;

        public StageInfo(int stage) {
            this.stage = stage;
        }

        public int getStage() {
            return stage;
        }
        public double getFailRate() {
            if(reached == 0)
                return 0;
            return (double) struggling /(double) reached;
        }
    }

    public static void main(String[] args) {
        solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        System.out.println();
        solution(4, new int[]{4, 4, 4, 4, 4});
    }
}
