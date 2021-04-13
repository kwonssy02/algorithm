// https://programmers.co.kr/learn/courses/30/lessons/12917
package Level1.문자열내림차순으로배치하기;

class Solution {
    public String solution(String s) {
        int length = s.length();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = s.charAt(i);
        }

        sort(arr, 0, length-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char)arr[i]);
        }
        return sb.toString();
    }
    
    public static void sort(int[] data, int l, int r) {
        int left = l;
        int right = r;
        int pivot = data[(l+r)/2];
        
        do{
            while(data[left] > pivot) left++;
            while(data[right] < pivot) right--;
            if(left <= right){    
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        }while (left <= right);
        
        
        if(l < right) sort(data, l, right);
        if(r > left) sort(data, left, r);
    }
}
