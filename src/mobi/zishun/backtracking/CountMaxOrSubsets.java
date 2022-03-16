package mobi.zishun.backtracking;

/*
 * 2044. 统计按位或能得到最大值的子集数目
给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
输入：nums = [3,2,1,5]
输出：6
解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]
链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets
 */
public class CountMaxOrSubsets {
    private int count = 0;

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int curValue = nums[0];
        int maxValue = curValue;
        for (int i = 1; i < n; i++) {
            curValue = curValue | nums[i];
            if (curValue > maxValue) {
                maxValue = curValue;
            }
        }
        dfs(nums, n, maxValue, 0, 0);
        return count;
    }

    // 每个长度为 n 比特的状态的按位或的值，都是可以在长度为 n - 1 比特的状态的按位或的值上计算出来的，而这个计算只需要消耗常数时间。
    // 参数 index 表示当前下标，curVal 表示当前下标之前的某个子集按位或值，
    // 这样就可以保存子集按位或的值的信息，并根据当前元素选择与否更新 curVal。
    // 当搜索到最后位置时，更新最大值和子集个数。
    private void dfs(int[] nums, int n, int maxValue, int index, int curValue) {
        // 每种情况到达终点时，判断当前记录值curValue是否等于最大值maxValue
        if (index == n) {
            if (curValue == maxValue) {
                count++;
            }
            return;
        }
        // 选择把当前元素计入curVal
        dfs(nums, n, maxValue, index + 1, curValue | nums[index]);
        // 或者排除当前元素，从下一个元素继续选择
        dfs(nums, n, maxValue, index + 1, curValue);
    }

    public static void main(String[] args) {
//        System.out.println(3 | 5); //7
//        System.out.println(2 | 5); //7
//        System.out.println(2 | 1 | 5); //7
        CountMaxOrSubsets m = new CountMaxOrSubsets();
        int[] nums = {3, 2, 1, 5};
        System.out.println(m.countMaxOrSubsets(nums));
    }
}
