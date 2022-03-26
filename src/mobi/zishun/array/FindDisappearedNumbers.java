package mobi.zishun.array;

import java.util.ArrayList;
import java.util.List;

/*
 * 448. 找到所有数组中消失的数字
给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
示例 1：
输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]
示例 2：
输入：nums = [1,1]
输出：[2]
提示：
n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
* https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            // 出现过的数字记录在其作为索引的位置（通过 %n 保证不影响后续元素继续遍历）
            nums[x] += n;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 哪个索引上的数字没有+=n过，说明代表这个索引代表的数字之前没有出现过
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers m = new FindDisappearedNumbers();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(m.findDisappearedNumbers(nums));
    }
}
