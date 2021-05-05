// https://programmers.co.kr/learn/courses/30/lessons/60062
package Programmers.Level3.외벽점검;

import java.util.*;

/*
    - n은 1 이상 200 이하인 자연수입니다.
    - weak의 길이는 1 이상 15 이하입니다.
    - 서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
    - 취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
    - weak의 원소는 0 이상 n - 1 이하인 정수입니다.
    - dist의 길이는 1 이상 8 이하입니다.
    - dist의 원소는 1 이상 100 이하인 자연수입니다.
    - 친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.
 */
class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        List<List<Integer>> distPermutations = new ArrayList<>();
        boolean[] visited = new boolean[dist.length];
        for (int i = 1; i <= dist.length; i++) {
            makeDistPermutations(0, i, new ArrayList<>(), dist, visited, distPermutations);
        }

//        for (int i = 0; i < distPermutations.size(); i++) {
//            final List<Integer> permu = distPermutations.get(i);
//            for (int j = 0; j < permu.size(); j++) {
//                System.out.print(permu.get(j) + " ");
//            }
//            System.out.println();
//        }

        for (List<Integer> p : distPermutations) {
//            System.out.print("permutation: ");
//            for (Integer e : p) {
//                System.out.print(e + " ");
//            }
//            System.out.println();

            int pIndex = 0;
            Set<Integer> weakVisited = new HashSet<>();
            for (int i = 0; i < weak.length; i++) {
                int start = weak[i];
                //TODO: start부터 시작하는 직선 array 만들고 취약점 점검

            }

            if (weakVisited.size() == weak.length) {
                return p.size();
            }
        }

        return -1;
    }

    public void makeDistPermutations(int people, int n, List<Integer> permutation, int[] dist, boolean[] visited, List<List<Integer>> target) {
        if (people == n) {
            target.add(permutation);
            return;
        }

        for (int i = dist.length - 1; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                final ArrayList<Integer> temp = new ArrayList<>(permutation);
                temp.add(dist[i]);
                makeDistPermutations(people + 1, n, temp, dist, visited, target);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
//        System.out.println(new Solution().solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}