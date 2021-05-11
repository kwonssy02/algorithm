// https://programmers.co.kr/learn/courses/30/lessons/76503
package Programmers.Level3.모두0으로만들기;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public long solution(int[] a, int[][] edges) {

        long sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum != 0)
            return -1;

        List<Integer>[] nodes = new List[a.length];
        for (int i = 0; i < a.length; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            nodes[u].add(v);
            nodes[v].add(u);
        }

        long[] weights = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            weights[i] = a[i];
        }

        return dfs(nodes, weights, 0, 0);
    }

    private long dfs(List<Integer>[] nodes, long[] weights, int currentNode, int parentNode) {
//        System.out.println("currentNode = " + currentNode + ", parentNode = " + parentNode);
        long count = 0;
        for (Integer node : nodes[currentNode]) {
            if (node != parentNode)
                count += dfs(nodes, weights, node, currentNode);
        }

        weights[parentNode] += weights[currentNode];
        count += Math.abs(weights[currentNode]);
        weights[currentNode] = 0;

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