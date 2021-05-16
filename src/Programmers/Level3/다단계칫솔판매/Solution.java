// https://programmers.co.kr/learn/courses/30/lessons/77486
package Programmers.Level3.다단계칫솔판매;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] sellers, int[] amounts) {
        Map<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            nameMap.put(enroll[i], i);
        }
        int[] tree = new int[enroll.length];
        int[] sum = new int[enroll.length];

        for (int i = 0; i < referral.length; i++) {
            if (referral[i].equals("-")) {
                tree[i] = -1;
                continue;
            }
            tree[i] = nameMap.get(referral[i]);
        }

        for (int i = 0; i < sellers.length; i++) {
            int amount = amounts[i] * 100;
            int childIndex = nameMap.get(sellers[i]);
            while (amount > 0) {
                sum[childIndex] += (amount - (int) (amount * 0.1));
                amount = (int) (amount * 0.1);

                int parentIndex = tree[childIndex];
                if (parentIndex == -1) break;
                childIndex = parentIndex;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        )));

        System.out.println(Arrays.toString(new Solution().solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4}
        )));
    }
}