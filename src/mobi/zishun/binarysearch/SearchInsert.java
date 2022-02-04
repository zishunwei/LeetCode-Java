package mobi.zishun.binarysearch;

/*
 * 35. 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。
链接：https://leetcode-cn.com/problems/search-insert-position
 */
public class SearchInsert {
    // 精简版
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int medium = (high - low) / 2 + low;
            if (target > nums[medium]) {
                low = medium + 1;
            } else {
                high = medium - 1;
            }
        }
        return low;
    }

    // 初版 - 代码更冗余 - 但部分情况可以提前终止循环
    public int searchInsertV2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int medium = 0;
        while (low <= high) {
            medium = (high - low) / 2 + low;
            if (nums[medium] == target) {
                return medium;
            } else if (target > nums[medium]) {
                low = medium + 1;
            } else {
                high = medium - 1;
            }
        }
        if (nums[medium] < target) {
            return medium + 1;
        } else {
            return medium;
        }
    }

    public static void main(String[] args) {
        SearchInsert si = new SearchInsert();
        int[] nums = {1, 2, 3, 5, 7, 9, 10, 11};
        System.out.println(si.searchInsert(nums, 8));
    }

}
