package mobi.zishun.array;

/*
 * 396. 旋转函数
给定一个长度为 n 的整数数组 nums 。
假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
返回 F(0), F(1), ..., F(n-1)中的最大值 。
生成的测试用例让答案符合 32 位 整数。

示例 1:
输入: nums = [4,3,2,6]
输出: 26
解释:
F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
提示:
n == nums.length
1 <= n <= 10^5
-100 <= nums[i] <= 100
* https://leetcode-cn.com/problems/rotate-function/
 */
public class MaxRotateFunction {
    // 迭代 - 公式推导
    // 记数组 nums 的元素之和为 numSum。
    // 根据公式，可以得到：
    // * F(0) = 0×nums[0] + 1×nums[1] + … + (n−1)×nums[n−1]
    // * F(1) = 1×nums[0] + 2×nums[1] +…+ 0×nums[n−1] = F(0) + numSum − n×nums[n−1]
    // 更一般地，当 1≤k<n 时，F(k) = F(k−1) + numSum − n×nums[n−k]。
    // 我们可以不停迭代计算出不同的 F(k)，并求出最大值。
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        // 所有元素之和
        int numSum = 0;
        int prev = 0;
        // 计算F(0) 和 numSum
        for (int i = 0; i < n; i++) {
            prev += i * nums[i];
            numSum += nums[i];
        }
        int res = prev;
        // 逐一计算 i 为起点的结果
        // 动态更新prev，即为上一个结果
        for (int i = 1; i < n; i++) {
            int cur = prev + numSum - n * nums[n - i];
            prev = cur;
            res = Math.max(res, cur);
        }
        return res;
    }

    // 暴力法 - 超出时间限制
    public int maxRotateFunctionV2(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            int start = i;
            int count = 0;
            while (count < n) {
                cur += count * nums[start];
                if (start < n - 1) {
                    start++;
                } else {
                    start = 0;
                }
                count++;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
