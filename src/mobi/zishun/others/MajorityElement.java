package mobi.zishun.others;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> numCounts = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!numCounts.containsKey(nums[i])) {
                numCounts.put(nums[i], 1);
            } else {
                numCounts.put(nums[i], numCounts.get(nums[i]) + 1);
            }
        }

        int[] resMap = new int[2];
        for (Map.Entry<Integer, Integer> numCount : numCounts.entrySet()) {
            if (numCount.getValue() > resMap[1]) {
                resMap[0] = numCount.getKey();
                resMap[1] = numCount.getValue();
            }
        }
        return resMap[0];

    }

    public static void main(String[] args) {

    }

}
