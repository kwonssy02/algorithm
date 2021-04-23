// https://programmers.co.kr/learn/courses/30/lessons/17686
package Programmers.Level2._3차_파일명정렬;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
        File[] fileNames = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = new File(files[i], i);
        }
        Arrays.sort(fileNames);

        String[] answer = new String[files.length];
        for (int i = 0; i < fileNames.length; i++) {
            answer[i] = fileNames[i].name;
        }
        return answer;
    }

    static Pattern pattern = Pattern.compile("^([a-zA-Z -]+)([0-9]{1,5})");

    class File implements Comparable<File> {

        String name;
        String head;
        int number;
        int rank;

        public File(String fileName, int rank) {
            name = fileName;
            this.rank = rank;
            Matcher m = pattern.matcher(fileName.toLowerCase());
            m.find();
            head = m.group(1);
            number = Integer.parseInt(m.group(2));
//            System.out.println("head = " + head);
//            System.out.println("number = " + number);
        }

        @Override
        public int compareTo(final File o) {
            int headCompare = head.compareTo(o.head);
            if (headCompare == 0) {
                int numberCompare = Integer.compare(number, o.number);
                if (numberCompare == 0) {
                    return rank - o.rank;
                }
                return numberCompare;
            }
            return headCompare;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        solution.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
    }
}
