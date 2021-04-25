// https://programmers.co.kr/learn/courses/30/lessons/43164
package Programmers.Level3.여행경로;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public String[] solution(String[][] tickets) {
        TreeMap<String, Integer> airportMap = new TreeMap();
        for (String[] ticket : tickets) {
            String departure = ticket[0];
            String destination = ticket[1];
            airportMap.put(departure, 0);
            airportMap.put(destination, 0);
        }

        final Iterator<Map.Entry<String, Integer>> iter = airportMap.entrySet().iterator();
        int index = 0;
        String[] airportNameArr = new String[airportMap.size()];
        while (iter.hasNext()) {
            final Map.Entry<String, Integer> next = iter.next();
            airportNameArr[index] = next.getKey();
            airportMap.put(next.getKey(), index);
            index++;
        }

        int[][] edges = new int[airportMap.size()][airportMap.size()];
        for (String[] ticket : tickets) {
            String departure = ticket[0];
            String destination = ticket[1];
            int departureIndex = airportMap.get(departure);
            int destinationIndex = airportMap.get(destination);
            edges[departureIndex][destinationIndex]++;
        }

        String dfs = dfs(airportMap.get("ICN") + "", airportMap.get("ICN"), 1, tickets.length, edges);
        final String[] split = dfs.split(",");
        String[] answer = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            answer[i] = airportNameArr[Integer.parseInt(split[i])];
        }

//        for (int i = 0; i < answer.length; i++) {
//            System.out.print(answer[i] + " ");
//        }
//        System.out.println();
        return answer;
    }

    public String dfs(String path, int currIndex, int numOfUsed, int numOfTickets, int[][] edges) {
        if (numOfUsed > numOfTickets) {
            return path;
        }

        for (int i = 0; i < edges[currIndex].length; i++) {
            if (edges[currIndex][i] > 0) {
                edges[currIndex][i]--;
                String dfs = dfs(path + "," + i, i, numOfUsed + 1, numOfTickets, edges);
                if (dfs != null)
                    return dfs;
                edges[currIndex][i]++;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[][]{
                new String[]{"ICN", "SFO"},
                new String[]{"ICN", "ATL"},
                new String[]{"SFO", "ATL"},
                new String[]{"ATL", "ICN"},
                new String[]{"ATL", "SFO"},
        });

        solution.solution(new String[][]{
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "SFO"},
                new String[]{"SFO", "ICN"},
                new String[]{"ICN", "ATL"},
        });
    }
}