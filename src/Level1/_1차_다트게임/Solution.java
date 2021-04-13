// https://programmers.co.kr/learn/courses/30/lessons/17682
package Level1._1차_다트게임;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public static int solution(String dartResult) {

        Pattern p = Pattern.compile("([01]?\\d)([SDT])([*#]?)");
        Matcher m = p.matcher(dartResult);

        List<C> list = new ArrayList<>();
        while(m.find()) {
            int num = Integer.parseInt(m.group(1));
            int pow = m.group(2).equals("S") ? 1 : m.group(2).equals("D") ? 2 : 3;
            int mode = m.group(3).equals("") ? 0 : m.group(3).equals("*") ? 1 : 2;

            list.add(new C(num, pow, mode));
        }

        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            C c = list.get(i);
            int pownum = (int)Math.pow(c.num, c.pow);
            switch (c.mode) {
                case 0:
                    answer += pownum;
                    break;
                case 1: // *
                    answer += pownum*2;
                    if (i != 0) {
                        C bc = list.get(i-1);
                        int bcpownum = (int)Math.pow(bc.num, bc.pow);

                        if(bc.mode == 0)
                            answer += bcpownum;
                        else if(bc.mode == 1)
                            answer += bcpownum*2;
                        else
                            answer -= bcpownum;
                    }
                    break;
                case 2: // #
                    answer -= pownum;
                    break;
            }
        }

        return answer;
    }

    static class C {
        int num;
        int pow;
        int mode; // 없음(0), 스타상(1), 아차상(2)

        public C(int num, int pow, int mode) {
            this.num = num;
            this.pow = pow;
            this.mode = mode;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));
        System.out.println(solution("1D2S#10S"));
        System.out.println(solution("1D2S0T"));
        System.out.println(solution("1S*2T*3S"));
        System.out.println(solution("1D#2S*3S"));
        System.out.println(solution("1T2D3D#"));
        System.out.println(solution("1D2S3T*"));
        System.out.println(solution("1S2D*3T*"));
        System.out.println(solution("1S*2D*3T*"));

    }
}
