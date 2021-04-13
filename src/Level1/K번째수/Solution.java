// https://programmers.co.kr/learn/courses/30/lessons/42748
package Level1.K번째수;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        for(int i=0; i<commands.length; i++) {
            int[] arr = new int[commands[i][1]- commands[i][0] + 1];
            for(int j=commands[i][0]-1, k=0; j<=commands[i][1]-1; j++, k++)
                arr[k] = array[j];

            sort(arr, 0, commands.length-1);
            answer[i] = arr[commands[i][2]-1];
        }
        
        return answer;
    }

    public static void sort(int[] arr, int left, int right) {

        int pivot = arr[(left+right)/2];
        int pl = left;
        int pr = right;

        do {
            while(pivot > arr[pl]) pl++;
            while(pivot < arr[pr]) pr--;
            
            if(pl <= pr) {
                int temp = arr[pl];
                arr[pl] = arr[pr];
                arr[pr] = temp;
                pl++;
                pr--;
            }
        } while (pl <= pr);

        if(left < pr) sort(arr, left, pr);
        if(right > pl) sort(arr, pl, right);
    }
}
