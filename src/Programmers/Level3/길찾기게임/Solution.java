// https://programmers.co.kr/learn/courses/30/lessons/42892
package Programmers.Level3.길찾기게임;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            if (o2.y - o1.y == 0)
                return o1.x - o2.x;
            return o2.y - o1.y;
        });

        for (int i = 0; i < nodeinfo.length; i++) {
            queue.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Node node = queue.poll();
        while (!queue.isEmpty()) {
            node.insert(queue.poll());
        }

        Queue<Integer> preorderQueue = new LinkedList<>();
        node.preorder(preorderQueue);
        Queue<Integer> postorderQueue = new LinkedList<>();
        node.postorder(postorderQueue);

        int numberCount = preorderQueue.size();

        int[][] answer = new int[2][numberCount];
        int index = 0;
        while (!preorderQueue.isEmpty()) {
            answer[0][index++] = preorderQueue.poll();
        }

        index = 0;
        while (!postorderQueue.isEmpty()) {
            answer[1][index++] = postorderQueue.poll();
        }

        return answer;
    }

    class Node {
        int number;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        public boolean insert(Node node) {
            if (node.y >= y)
                return false;

            if (node.x < x) {
                if (left == null) {
                    left = node;
                    return true;
                }
                return left.insert(node);
            } else if (x < node.x) {
                if (right == null) {
                    right = node;
                    return true;
                }
                return right.insert(node);
            }

            return false;
        }

        public void preorder(Queue<Integer> queue) {
            queue.add(number);
            if (left != null) {
                left.preorder(queue);
            }
            if (right != null) {
                right.preorder(queue);
            }
        }

        public void postorder(Queue<Integer> queue) {
            if (left != null) {
                left.postorder(queue);
            }
            if (right != null) {
                right.postorder(queue);
            }
            queue.add(number);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "number=" + number +
                    '}';
        }
    }

    public static void main(String[] args) {
        new Solution().solution(new int[][]{
                new int[]{5, 3},
                new int[]{11, 5},
                new int[]{13, 3},
                new int[]{3, 5},
                new int[]{6, 1},
                new int[]{1, 3},
                new int[]{8, 6},
                new int[]{7, 2},
                new int[]{2, 2}
        });
    }
}