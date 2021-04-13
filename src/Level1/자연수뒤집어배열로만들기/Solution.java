// https://programmers.co.kr/learn/courses/30/lessons/12932
package Level1.자연수뒤집어배열로만들기;

class Solution {
  public int[] solution(long n) {
      String str = ""+n;
        int[] arr = new int[str.length()];
        for(int i=0;i<str.length();i++) {
            arr[i] = str.charAt(str.length()-1-i)-'0';
        }
        return arr;
  }
}
