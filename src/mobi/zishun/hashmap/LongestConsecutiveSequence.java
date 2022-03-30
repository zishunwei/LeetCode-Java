package mobi.zishun.hashmap;

import java.util.HashSet;
import java.util.Set;

/*
 * 128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
提示：
0 <= nums.length <= 105
-10^9 <= nums[i] <= 10^9
* https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int res = 0;
        for (int num : hashSet) {
            if (!hashSet.contains(num - 1)) {
                int end = num + 1;
                while (hashSet.contains(end)) {
                    end++;
                }
                res = Math.max(res, end - num);
            }
        }
        return res;
    }

}
