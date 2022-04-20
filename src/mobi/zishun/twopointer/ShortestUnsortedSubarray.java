package mobi.zishun.twopointer;

import java.util.Arrays;

/*
 * 581. 最短无序连续子数组
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
请你找出符合题意的 最短 子数组，并输出它的长度。

示例 1：
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：
输入：nums = [1,2,3,4]
输出：0
提示：
1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5
进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
* https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedSubarray {
    // 一次遍历 - 双指针 -O(n)
    // 无序子数组中最小元素的正确位置可以决定左边界，最大元素的正确位置可以决定右边界。
    // 假设把这个数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，
    // 但满足最小值大于左段的最大值，最大值小于右段的最小值。
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 无序子数组中最小元素
        int min = nums[n - 1];
        // 无序子数组中最大元素
        int max = nums[0];
        // 无序子数组左边界
        int left = 0;
        // 无序子数组右边界
        int right = -1;
        for (int i = 0; i < n; i++) {
            // 从左到右维持最大值，寻找右边界right
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
            //从右到左维持最小值，寻找左边界left
            if (nums[n - 1 - i] > min) {
                left = n - 1 - i;
            } else {
                min = nums[n - 1 - i];
            }
        }
        // right == -1 说明right没有更新过（nums[i]一直小于nums[i-1]）即nums本身就是升序排序，无需排序返回0
//        return right == -1 ? 0 : right - left + 1;
        return right - left + 1;
    }

    // 排序 + 双指针 - O(nlog(n))
    public int findUnsortedSubarrayV2(int[] nums) {
        int n = nums.length;
        int[] numsSorted = new int[n];
        System.arraycopy(nums, 0, numsSorted, 0, n);
        Arrays.sort(numsSorted);
        int left = 0;
        int right = n - 1;
        while (left < right) {
            boolean isFinished = true;
            if (numsSorted[left] == nums[left]) {
                left++;
                isFinished = false;
            }
            if (numsSorted[right] == nums[right]) {
                right--;
                isFinished = false;
            }
            if (isFinished) {
                break;
            }
        }
        return left == right ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        ShortestUnsortedSubarray m = new ShortestUnsortedSubarray();
        int[] nums = {1, 3, 2, 4, 5};
        System.out.println(m.findUnsortedSubarray(nums));
    }

}
