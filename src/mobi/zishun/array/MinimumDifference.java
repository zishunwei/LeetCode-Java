package mobi.zishun.array;

import java.util.Arrays;

/*
 * 1984. 学生分数的最小差值
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
返回可能的 最小差值 。
链接：https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 */
public class MinimumDifference {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        // 滑动窗口
        for (int i = 0; i < n - k + 1; i++) {
            int diff = nums[i + k - 1] - nums[i];
            if (diff < res) {
                res = diff;
            }
        }
        return res;
    }

}
