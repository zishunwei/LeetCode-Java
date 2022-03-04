package mobi.zishun.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 2104. 子数组范围和
给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
返回 nums 中 所有 子数组范围的 和 。
子数组是数组中一个连续 非空 的元素序列。
输入：nums = [1,2,3]
输出：4
解释：nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0
[2]，范围 = 2 - 2 = 0
[3]，范围 = 3 - 3 = 0
[1,2]，范围 = 2 - 1 = 1
[2,3]，范围 = 3 - 2 = 1
[1,2,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
* 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges
 */
public class SubArrayRanges {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n - 1; i++) {
            int minValue = nums[i];
            int maxValue = nums[i];
            for (int j = i + 1; j < n; j++) {
                minValue = Math.min(minValue, nums[j]);
                maxValue = Math.max(maxValue, nums[j]);
                res += maxValue - minValue;
            }
        }
        return res;
    }

    // 题目要求所有区间的最大值减去最小值的和，相当于求每个数作为最大值出现在的区间个数 和 作为最小值出现的区间个数。
    // 最大值维护一个单调递减栈，当当前值比前面的值大时，意味着栈里面的这些小的元素的右边最远到当前值，
    // 而栈里面这些小的元素作为最大值始终是它加入时前一个最大值的坐标。
    // 最小值与最大值思路一致，只是逻辑相反。
    public long subArrayRangesV2(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        long max = 0;
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peekLast()] < nums[i])) {
                int j = stack.pollLast();
                max += (long) nums[j] * (i - j) * (j - (stack.isEmpty() ? -1 : stack.peekLast()));
            }
            stack.offerLast(i);
        }
        stack = new LinkedList<>();
        long min = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peekLast()] > nums[i])) {
                int j = stack.pollLast();
                min += (long) nums[j] * (i - j) * (j - (stack.isEmpty() ? -1 : stack.peekLast()));
            }
            stack.offerLast(i);
        }
        return max - min;
    }


}
