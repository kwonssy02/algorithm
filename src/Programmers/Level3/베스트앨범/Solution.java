// https://programmers.co.kr/learn/courses/30/lessons/42579
package Programmers.Level3.베스트앨범;

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Album> map = new HashMap<>();
        for (int i = genres.length - 1; i >= 0; i--) {
            final String genre = genres[i];
            final int play = plays[i];
            if (map.get(genre) == null) {
                map.put(genre, new Album(play, play, i, -1, -1));
            } else {
                map.get(genre).updateTop2(play, i);
            }
        }

        List<Album> list = new ArrayList<>();
        for (Map.Entry<String, Album> entry : map.entrySet()) {
            list.add(entry.getValue());
        }

        int[] temp = new int[list.size() * 2];
        list.sort(((o1, o2) -> o2.totalPlay - o1.totalPlay));
        int index = 0;
        for (Album album : list) {
            temp[index++] = album.firstIndex;
            if (album.secondIndex != -1)
                temp[index++] = album.secondIndex;
        }

        int[] answer = Arrays.copyOf(temp, index);
        ;
//        for (int i = 0; i < answer.length; i++) {
//            System.out.print(answer[i] + " ");
//        }
        return answer;
    }

    class Album {
        int totalPlay;
        int firstPlay;
        int firstIndex;
        int secondPlay;
        int secondIndex;

        public Album(int totalPlay, int firstPlay, int firstIndex, int secondPlay, int secondIndex) {
            this.totalPlay = totalPlay;
            this.firstPlay = firstPlay;
            this.firstIndex = firstIndex;
            this.secondPlay = secondPlay;
            this.secondIndex = secondIndex;
        }

        public void updateTop2(int play, int index) {
            totalPlay += play;
            if (play >= firstPlay) {
                secondPlay = firstPlay;
                secondIndex = firstIndex;
                firstPlay = play;
                firstIndex = index;
            } else if (play >= secondPlay) {
                secondPlay = play;
                secondIndex = index;
            }
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
    }
}