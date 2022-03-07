package mobi.zishun.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 740. 删除并获得点数
给你一个整数数组nums，你可以对它进行一些操作。
每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
* 1 <= nums.length <= 2 * 104
* 1 <= nums[i] <= 10^4
链接：https://leetcode-cn.com/problems/delete-and-earn
 */
public class DeleteAndEarn {
    // 计数 + 动态规划 - O(N+M)，其中 N 是数组nums的长度，M是nums中元素的最大值。
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        // 计数排序思想，记录出现频率
        int[] freq = new int[max + 1];
        for (int num : nums) {
            freq[num]++;
        }
        // 初始化
        int[] dp = new int[max + 1];
        dp[0] = 0;
        dp[1] = freq[1];
        // 动态规划
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], freq[i] * i + dp[i - 2]);
        }
        return dp[max];
    }

    // 排序 + 动态规划 - O(nlogn)
    // 注意到若 nums 中不存在某个元素 x，则选择任一小于 x 的元素不会影响到大于 x 的元素的选择。
    // 因此我们可以将 nums 排序后，将其划分成若干连续子数组，子数组内任意相邻元素之差不超过 1。
    // 对每个子数组按照方法一的动态规划过程计算出结果，累加所有结果即为答案。
    public int deleteAndEarnV2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        List<Integer> sum = new ArrayList<>();
        sum.add(nums[0]);
        int size = 1;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            if (cur == nums[i - 1]) {
                sum.set(size - 1, sum.get(size - 1) + cur);
            } else if (cur == nums[i - 1] + 1) {
                sum.add(cur);
                ++size;
            } else {
                res += findMaxEarn(sum);
                sum.clear();
                sum.add(cur);
                size = 1;
            }
        }
        res += findMaxEarn(sum);
        return res;
    }

    private int findMaxEarn(List<Integer> nums) {
        int size = nums.size();
        if (size == 1) {
            return nums.get(0);
        }
        int first = nums.get(0), second = Math.max(nums.get(0), nums.get(1));
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums.get(i), second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        DeleteAndEarn m = new DeleteAndEarn();
        System.out.println(m.deleteAndEarn(nums));
    }

}
