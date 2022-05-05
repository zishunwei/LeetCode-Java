package mobi.zishun.slidingwindow;

/*
 * 713. 乘积小于 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
示例 1：
输入：nums = [10,5,2,6], k = 100
输出：8
解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
示例 2：
输入：nums = [1,2,3], k = 0
输出：0
提示:
1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 10^6
* https://leetcode-cn.com/problems/subarray-product-less-than-k/
 */
public class NumSubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }
        //记录连续数组的组合个数
        int res = 0;
        int start = 0;
        int end = 0;
        //创建一个变量记录路上的乘积
        int product = 1;
        //用右指针遍历整个数组，每次循环右指针右移一次
        while (end < nums.length) {
            //记录乘积
            product *= nums[end];
            //当大于等于k，左指针右移并把之前左指针的数除掉
            while (product >= k) {
                product /= nums[start];
                start++;
            }
            //每次右指针位移到一个新位置，应该加上 x 种数组组合：
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            //  共有 right - left + 1 种
            res += end - start + 1;
            //右指针右移
            end++;
        }
        return res;
    }

}
