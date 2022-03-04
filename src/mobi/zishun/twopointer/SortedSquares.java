package mobi.zishun.twopointer;

/*
 * 977. 有序数组的平方
给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
* 输入：nums = [-4,-1,0,3,10]
输出：[0,1,9,16,100]
解释：平方后，数组变为 [16,1,0,9,100]
排序后，数组变为 [0,1,9,16,100]
链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 */
public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i = 0;
        for (; i < n; i++) {
            if (nums[i] >= 0) {
                break;
            }
        }
        // nums[j] 及左边的元素为负
        // nums[i] 及右边的元素为正
        int j = i - 1;
        int[] res = new int[n];
        int count = 0;
        while (i < n || j >= 0) {
            if (i == n) {
                int left = nums[j];
                res[count] = left * left;
                j--;
            } else if (j < 0) {
                int right = nums[i];
                res[count] = right * right;
                i++;
            } else {
                int right = nums[i];
                int left = -nums[j];
                if (right <= left) {
                    res[count] = right * right;
                    i++;
                } else {
                    res[count] = left * left;
                    j--;
                }
            }
            count++;
        }
        return res;
    }

}
