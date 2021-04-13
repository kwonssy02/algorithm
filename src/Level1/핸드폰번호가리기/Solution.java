// https://programmers.co.kr/learn/courses/30/lessons/12948
package Level1.핸드폰번호가리기;

class Solution {
    public String solution(String phone_number) {
        String temp = "********************";
        
        return temp.substring(0, phone_number.length()-4) + phone_number.substring(phone_number.length()-4, phone_number.length());
    }
}
