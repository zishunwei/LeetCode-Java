package mobi.zishun.math;

/*
 * 238. 除自身以外数组的乘积
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请不要使用除法，且在 O(n) 时间复杂度内完成此题。

示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]
示例 2:
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
提示：
2 <= nums.length <= 105
-30 <= nums[i] <= 30
保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
* https://leetcode-cn.com/problems/product-of-array-except-self/
 */
public class ProductExceptSelf {
    // 空间复杂度 - O(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 索引左边所有元素的乘积
        int[] leftProduct = new int[n];
        leftProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        }
        // 索引右边所有元素的乘积
        int[] rightProduct = new int[n];
        rightProduct[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }
        // 计算结果
        // 除 nums[i] 之外其余各元素的乘积 = nums[i]左边的元素乘积 * nums[i]右边的元素乘积
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftProduct[i] * rightProduct[i];
        }
        return res;
    }

    // 空间复杂度 - O(1)
    // 利用结果数组保存临时数据 - 动态迭代更新
    public int[] productExceptSelfV2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // res先保存索引左边所有元素的乘积
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // 计算索引右边所有元素的乘积的同时直接计算最终结果并保存
        int rightProduct = 1; // 右边乘积 - 滚动更新
        for (int i = n - 2; i >= 0; i--) {
            rightProduct = nums[i + 1] * rightProduct;
            res[i] = res[i] * rightProduct;
        }
        return res;
    }


}
