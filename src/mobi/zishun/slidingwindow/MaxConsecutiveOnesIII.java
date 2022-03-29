package mobi.zishun.slidingwindow;

/*
 * 1004. 最大连续1的个数 III
给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。

示例 1：
输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：
输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。
提示：
1 <= nums.length <= 105
nums[i] 不是 0 就是 1
0 <= k <= nums.length
* https://leetcode-cn.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        //  双指针，表示当前遍历的区间[left, right]，闭区间
        int left = 0;
        int right = 0;
        // 记录当前区间0的数量（需要被翻转的数量）
        int zeroCount = 0;
        // 当右边的指针没有搜索到 数组/字符串 的结尾
        while (right < n) {
            // 右指针指向0，先添加进zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }
            // zeroCount已大于k，循环移动left
            // 区间从左去掉0
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // 获取结果
            res = Math.max(res, right - left + 1);
            // 右指针向右移
            right++;
        }
        return res;
    }
}
