package mobi.zishun.others;
/*
53. 最大子序和
 */

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > sum) {
                sum = sum + nums[i];
            } else {
                sum = nums[i];
            }
            res = Math.max(sum, res);
        }
        return res;
    }

}
