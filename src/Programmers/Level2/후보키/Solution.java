// https://programmers.co.kr/learn/courses/30/lessons/42890
package Programmers.Level2.후보키;

import java.util.*;

//TODO: 단순 풀이법 찾기
class Solution {
    /*
    private boolean possi(List<Integer> list, int now){
        for(int i = 0; i < list.size(); i++){
            if ( (list.get(i) & now) == list.get(i)) return false;
        }
        return true;
    }
    public int solution(String[][] relation) {
        int n = relation.length;        // 가로
        int m = relation[0].length;     // 세로

        List<Integer> list = new ArrayList<>();

        for(int i = 1; i < (1<<m); i++){ // 1~2^m loop
            Set<String> s = new HashSet<>();
            for(int j = 0; j < n; j++){ // relation loop
                String now = "";
                for(int k = 0; k < m; k++){
                    if( (i & (1<<k)) > 0 ){
                        // i = 1, k = 0 -> 1 & 0 = 0
                        // i = 1, k = 1 -> 1 & 2 = 0
                        // i = 1, k = 2 -> 1 & 4 = 0
                        // System.out.print((i & (i<<k)) + " ");
                        now += relation[j][k];
                        // System.out.println(relation[j][k]);
                    }
                }
                s.add(now);
            }
            if(s.size() == n && possi(list, i) ){
                list.add(i);
            }
        }
        return list.size();
    }
     */

    public int solution(String[][] relation) {
        // 모든 Attribute에 대해 후보키 시뮬레이션

        // 원소 개수가 작은 순서로 Tuple을 생성
        // Tuple 순서대로 후보키 시뮬레이션 -> 후보키 가능 Tuple 따로 저장
        // 후보키 시뮬레이션 시, Tuple의 sub tuple이 후보키 가능할 시, skip
        int numOfAttributes = relation[0].length;
        List<List<Integer>> tuples = new ArrayList<>();

        for (int i = 0; i < numOfAttributes; i++) {
            tuples.add(Arrays.asList(i));
        }

        int lastIndex = 0;
        for (int i = 2; i <= numOfAttributes; i++) {
            int size = tuples.size();
            for (int j = lastIndex; j < size; j++) {
                List<Integer> temp = new ArrayList<>(tuples.get(j));
                for (int k = temp.get(temp.size() - 1) + 1; k < numOfAttributes; k++) {
                    List<Integer> newTuple = new ArrayList<>(temp);
                    newTuple.add(k);
                    tuples.add(newTuple);
                }
            }
            lastIndex = size;
        }

        for (int i = 0; i < tuples.size(); i++) {
            List<Integer> tuple = tuples.get(i);
            Set<String> set = new HashSet<>();
            boolean isKey = true;
            for (int j = 0; j < relation.length; j++) {
                String temp = "";
                for (Integer index : tuple) {
                    temp += relation[j][index] + ",";
                }
                if (!set.add(temp)) {
                    isKey = false;
                    break;
                }
            }
            if (isKey) {
                for (int k = i + 1; k < tuples.size(); k++) {
                    if (tuples.get(k).containsAll(tuple)) {
                        tuples.remove(k--);
                    }
                }
            } else {
                tuples.remove(i--);
            }
        }

        return tuples.size();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solution(new String[][]{
                new String[]{"100", "ryan", "music", "2"},
                new String[]{"200", "apeach", "math", "2"},
                new String[]{"300", "tube", "computer", "3"},
                new String[]{"400", "con", "computer", "4"},
                new String[]{"500", "muzi", "music", "3"},
                new String[]{"600", "apeach", "music", "2"}
        }));
    }
}
