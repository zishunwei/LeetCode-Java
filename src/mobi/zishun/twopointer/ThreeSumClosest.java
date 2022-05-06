package mobi.zishun.twopointer;

import java.util.Arrays;

/*
 * 16. 最接近的三数之和
给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
返回这三个数的和。
假定每组输入只存在恰好一个解。

示例 1：
输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
提示：
3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-10^4 <= target <= 10^4
* https://leetcode-cn.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
    // 排序 + 双指针优化
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            // 避免对相等的i重复进行下列枚举
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第三重循环对应的指针
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int cur = nums[i] + nums[j] + nums[k];
                if (cur == target) {
                    return target;
                }
                if (Math.abs(cur - target) < Math.abs(res - target)) {
                    res = cur;
                }
                if (cur > target) {
                    // 如果和大于 target，向前移动 k指针
                    // 移动到下一个不相等的元素
                    int curNum = nums[k];
                    do {
                        k--;
                    } while (j < k && nums[k] == curNum);
                } else {
                    int curNum = nums[j];
                    do {
                        j++;
                    } while (j < k && nums[j] == curNum);
                }
            }
        }
        return res;
    }

    // 暴力法 - O(n3)
    public int threeSumClosestV2(int[] nums, int target) {
        int n = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int cur = nums[i] + nums[j] + nums[k];
                    if (Math.abs(cur - target) < Math.abs(res - target)) {
                        res = cur;
                    }
                }
            }
        }
        return res;
    }

}
