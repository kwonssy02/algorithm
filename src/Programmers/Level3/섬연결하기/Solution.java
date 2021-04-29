// https://programmers.co.kr/learn/courses/30/lessons/42861
package Programmers.Level3.섬연결하기;

import java.util.*;

// MST (Minimum Spanning Tree 만들기)
// n개 정점과 (n-1)n/2개 이하의 간선이므로 Kruskal/Prim Algorithm 중 Kruskal 적합
class Solution {

    // Kruskal
    public int solution(int n, int[][] costs) {

        Arrays.sort(costs, Comparator.comparingInt(o -> o[2])); // 간선을 오름차순으로 정리
        int[] root = new int[n]; // 각 vertex tree의 root 저장
        for (int i = 0; i < root.length; i++)
            root[i] = i; // 초기엔 모두 연결안된 상태

        int totalWeight = 0;
        for (int[] cost : costs) {
            int source = cost[0];
            int target = cost[1];
            int weight = cost[2];

            if(find(root, source) != find(root, target)) { // root가 다르다면 (두 tree가 연결되지 않았다면) 연결
                union(root, source, target);
                totalWeight += weight;
            }
        }

        return totalWeight;
    }

    private void union(int[] root, int vertexA, int vertexB) {
        int rootA = find(root, vertexA);
        int rootB = find(root, vertexB);
        root[rootB] = rootA; // B tree가 A tree에 붙도록 root 조정
    }

    private int find(int[] root, int vertex) {
        if(root[vertex] == vertex) // 해당 vertex가 root라면 root[vertex]에 vertex가 저장되어있음.
            return vertex;
        return find(root, root[vertex]); // 상위 vertex 찾으러 가라
    }


    // Prim
    /*
    class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public int solution(int n, int[][] costs) {

        List<List<Edge>> edges = new ArrayList<>(); // 각 vertex 에 연결된 edge list 를 저장
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] cost : costs) {
            int source = cost[0];
            int target = cost[1];
            int weight = cost[2];
            edges.get(source).add(new Edge(target, weight));
            edges.get(target).add(new Edge(source, weight));
        }

        Queue<Integer> vertexQueue = new LinkedList<>();
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        int totalWeight = 0;

        vertexQueue.add(0);
        while (!vertexQueue.isEmpty()) {

            Integer vertex = vertexQueue.poll();
            visited[vertex] = true;

            for (Edge edge : edges.get(vertex)) { // vertex 에 연결된 edge를 edgeQueue에 넣기
                if (!visited[edge.vertex]) { // 방문하지 않은 vertex를 향한 edge만 추가
                    edgeQueue.add(edge);
                }
            }

            while (!edgeQueue.isEmpty()) {
                Edge edge = edgeQueue.poll(); // 간선의 weight가 작은 순서대로 뽑힘.
                if (!visited[edge.vertex]) { // 방문하지 않은 vertex를 향한 edge일 시, 간선 선택
                    vertexQueue.add(edge.vertex);
                    totalWeight += edge.weight;
                    break;
                }
            }
        }

        return totalWeight;
    }

     */

    public static void main(String[] args) {
        System.out.println(new Solution().solution(4, new int[][]{
                new int[]{0, 1, 1},
                new int[]{0, 2, 2},
                new int[]{1, 2, 5},
                new int[]{1, 3, 1},
                new int[]{2, 3, 8}
        }));

        System.out.println(new Solution().solution(5, new int[][]{
                new int[]{0, 1, 5},
                new int[]{1, 2, 3},
                new int[]{2, 3, 3},
                new int[]{3, 1, 2},
                new int[]{3, 0, 4},
                new int[]{2, 4, 6},
                new int[]{4, 0, 7}
        }));

        System.out.println(new Solution().solution(5, new int[][]{
                new int[]{0, 1, 1},
                new int[]{3, 1, 1},
                new int[]{0, 2, 2},
                new int[]{0, 3, 2},
                new int[]{0, 4, 100}
        }));
    }
}