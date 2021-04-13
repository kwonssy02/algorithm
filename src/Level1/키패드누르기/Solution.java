// https://programmers.co.kr/learn/courses/30/lessons/67256
package Level1.키패드누르기;

class Solution {

    static int lx = 0, ly = 0;
    static int rx = 2, ry = 0;
    static int[] xpad = new int[]{1, 0, 1, 2, 0, 1, 2, 0, 1, 2};
    static int[] ypad = new int[]{0, 3, 3, 3, 2, 2, 2, 1, 1, 1};

    public static String solution(int[] numbers, String hand) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(press(numbers[i], hand));
            
        }
        
        return sb.toString();
    }

    public static String press(int num, String hand) {

        switch(num) {
            case 1:
            case 4:
            case 7:
                lx = xpad[num]; 
                ly = ypad[num];
                return "L";
            case 2:
            case 5:
            case 8:
            case 0:
                int distanceFromL = Math.abs(xpad[num]-lx) + Math.abs(ypad[num]-ly);
                int distanceFromR = Math.abs(xpad[num]-rx) + Math.abs(ypad[num]-ry);
                if (distanceFromL > distanceFromR) {
                    rx = xpad[num]; 
                    ry = ypad[num];
                    return "R";
                } else if (distanceFromL < distanceFromR) {
                    lx = xpad[num]; 
                    ly = ypad[num];
                    return "L";
                } else if (hand.equals("right")) {
                    rx = xpad[num]; 
                    ry = ypad[num];
                    return "R";
                } else {
                    lx = xpad[num]; 
                    ly = ypad[num];
                    return "L";
                }
            case 3:
            case 6:
            case 9:
                rx = xpad[num]; 
                ry = ypad[num];
                return "R";
        }

        return "";
    }

}
