// https://programmers.co.kr/learn/courses/30/lessons/42746
package Level2.가장큰수;

import java.util.Arrays;

class Solution {
    public static String solution(int[] numbers) {
        String[] arr = Arrays.stream(numbers).mapToObj(Integer::toString).toArray(String[]::new);
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
//            System.out.println(s);
        }
        return sb.toString().replaceFirst("^0+(?!$)", "");
    }

    public static void main(String[] args) {
        assert solution(new int[]{6, 10, 2}).equals("6210");
        assert solution(new int[]{3, 30, 34, 5, 9}).equals("9534330");
        assert solution(new int[]{10, 101}).equals("10110");
        assert solution(new int[]{121, 12111}).equals("12112111");
        assert solution(new int[]{121, 12115}).equals("12115121");
        assert solution(new int[]{1, 11, 111, 1111}).equals("1111111111");
        assert solution(new int[]{0, 0, 0, 0, 0, 0}).equals("0");
        assert solution(new int[]{15, 151}).equals("15151");
        assert solution(new int[]{21, 211}).equals("21211");
        assert solution(new int[]{12, 121}).equals("12121");
        assert solution(new int[]{10, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).equals("987654321101000");
        assert solution(new int[]{10, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).equals("987654321101000");
        assert solution(new int[]{1000, 100}).equals("1001000");
        assert solution(new int[]{0, 5, 10, 15, 20}).equals("52015100");
        assert solution(new int[]{1000, 0, 5, 99, 100}).equals("99510010000");
        assert solution(new int[]{151, 15, 1}).equals("151511");
    }

}
