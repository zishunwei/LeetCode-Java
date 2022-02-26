package mobi.zishun.greedy;

/*
 * 2016. 增量元素之间的最大差值 == 买股票问题
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
* 输入：nums = [7,1,5,4]
输出：4
解释：
最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 > 4 ，但 i > j 不满足题面要求，所以 6 不是有效的答案。
链接：https://leetcode-cn.com/problems/maximum-difference-between-increasing-elements
 */
public class MaximumDifference {
    public int maximumDifference(int[] nums) {
        int maxDiff = -1;
        // 前缀最小值
        int premin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > premin) {
                maxDiff = Math.max(maxDiff, nums[i] - premin);
            } else {
                premin = nums[i];
            }
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        MaximumDifference m = new MaximumDifference();
        int[] nums = {1, 5, 2, 10};
        System.out.println(m.maximumDifference(nums));
    }
}
