// https://programmers.co.kr/learn/courses/30/lessons/76503
package Programmers.Level3.모두0으로만들기;

import java.util.ArrayList;
import java.util.List;

//TODO queue 이용해 leaf 노드부터 root 로 올리는 구현 방법 찾기
class Solution {

    List<Integer>[] nodes;
    long[] weights;

    public long solution(int[] a, int[][] edges) {

        long sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum != 0)
            return -1;

        nodes = new List[a.length];
        for (int i = 0; i < a.length; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            nodes[u].add(v);
            nodes[v].add(u);
        }

        weights = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            weights[i] = a[i];
        }

        return dfs(0, 0);
    }

    // currentNode의 인접 node들로부터 weight 흡수 후, parentNode로 weight 옮기기
    private long dfs(int currentNode, int parentNode) {
        long count = 0;
        for (int i = 0; i < nodes[currentNode].size(); i++) {
            int node = nodes[currentNode].get(i);
            if (node != parentNode)
                count += dfs(node, currentNode);
        }

        weights[parentNode] += weights[currentNode];
        count += Math.abs(weights[currentNode]);

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{-5, 0, 2, 1, 2}, new int[][]{
                new int[]{0, 1},
                new int[]{3, 4},
                new int[]{2, 3},
                new int[]{0, 3}
        }));

        System.out.println(new Solution().solution(new int[]{0, 1, 0}, new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
        }));
    }
}