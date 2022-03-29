package mobi.zishun.slidingwindow;

import java.util.TreeMap;

/*
 * 1438. 绝对差不超过限制的最长连续子数组
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
* 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
如果不存在满足条件的子数组，则返回 0 。

示例 1：
输入：nums = [8,2,4,7], limit = 4
输出：2
解释：所有子数组如下：
[8] 最大绝对差 |8-8| = 0 <= 4.
[8,2] 最大绝对差 |8-2| = 6 > 4.
[8,2,4] 最大绝对差 |8-2| = 6 > 4.
[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
[2] 最大绝对差 |2-2| = 0 <= 4.
[2,4] 最大绝对差 |2-4| = 2 <= 4.
[2,4,7] 最大绝对差 |2-7| = 5 > 4.
[4] 最大绝对差 |4-4| = 0 <= 4.
[4,7] 最大绝对差 |4-7| = 3 <= 4.
[7] 最大绝对差 |7-7| = 0 <= 4.
因此，满足题意的最长子数组的长度为 2 。
提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
* https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestContinuousSubarrayAbsoluteDiff {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int res = 0;
        int left = 0;
        int right = 0;
        // 有序列表（红黑树），key是元素值，value为出现的次数
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (right < n) {
            // right索引元素入表
            treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            // 循环直到满足条件
            while (treeMap.lastKey() - treeMap.firstKey() > limit) {
                // 从left删除数据
                treeMap.put(nums[left], treeMap.get(nums[left]) - 1);
                // 判断已经没有nums[left]元素了
                if (treeMap.get(nums[left]) == 0) {
                    treeMap.remove(nums[left]);
                }
                left++;
            }
            // 更新结果
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

}
