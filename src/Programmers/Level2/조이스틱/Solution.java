// https://programmers.co.kr/learn/courses/30/lessons/42860
package Programmers.Level2.조이스틱;

class Solution {

    public static int setAlphabet(char c) {
        return Math.min(c - 'A', 'A' + 26 - c);
    }

    public static int solution(String name) {
        int[] arr = new int[name.length()];
        int toChangeCount = name.length(), index = 0, answer = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                arr[i] = 1;
                toChangeCount--;
            }
        }

        while (true) {
            int move = setAlphabet(name.charAt(index));
            if (move > 0) {
                arr[index] = 1;
                toChangeCount--;
                answer += move;
            }
            if (toChangeCount == 0)
                break;

            int closestRightDistance = 0;
            int closestRightIndex = 0;
            for (int i = (index + 1 == name.length() ? 0 : index + 1); i < name.length(); i++) {
                closestRightDistance++;
                if (arr[i] == 0) {
                    closestRightIndex = i;
                    break;
                }
                if (i == name.length() - 1)
                    i = -1;
            }
            int closestLeftDistance = 0; // min(오른쪽으로 갔을 때 최단거리, 왼쪽으로 갔을 때 최단거리)
            int closestLeftIndex = 0;
            for (int i = (index - 1 == -1 ? name.length() - 1 : index - 1); i >= 0; i--) {
                closestLeftDistance++;
                if (arr[i] == 0) {
                    closestLeftIndex = i;
                    break;
                }
                if (i == 0)
                    i = name.length();
            }

            if (closestLeftDistance < closestRightDistance) {
                index = closestLeftIndex;
                answer += closestLeftDistance;
            } else {
                index = closestRightIndex;
                answer += closestRightDistance;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        assert solution("JEROEN") == 56;
        assert solution("JAN") == 23;
    }
}

/*
▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동

예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.
- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
 */