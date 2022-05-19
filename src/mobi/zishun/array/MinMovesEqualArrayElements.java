package mobi.zishun.array;

/*
 * 453. 最小操作次数使数组元素相等
给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。
* 返回让数组所有元素相等的最小操作次数。
*
示例 1：
输入：nums = [1,2,3]
输出：3
解释：
只需要3次操作（注意每次操作会增加两个元素的值）：
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
提示：
n == nums.length
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
答案保证符合 32-bit 整数
* https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/
 */
public class MinMovesEqualArrayElements {
    // 每次操作将会使 n - 1 个元素增加 1  ==  每次操作使1个元素减少1
    // 最终结果为所有元素减少1直到所有元素等于最小元素，操作次数等于每个元素-最小元素的和
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        int res = 0;
        for (int num : nums){
            res += num - min;
        }
        return res;
    }

}
