package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
示例 1：
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
提示：
1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
* https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, n, 0, new ArrayList<Integer>(), results);
        return results;
    }

    private void dfs(int[] nums, int n, int i, List<Integer> temp, List<List<Integer>> results) {
        if (i == n) {
            results.add(new ArrayList<>(temp));
            return;
        }
        // 跳过当前元素
        dfs(nums, n, i + 1, temp, results);
        // 添加当前元素
        temp.add(nums[i]);
        dfs(nums, n, i + 1, temp, results);
        temp.remove(temp.size() - 1); //回溯
    }

}
