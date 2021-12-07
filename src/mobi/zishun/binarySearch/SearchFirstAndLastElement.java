package mobi.zishun.binarySearch;

import java.util.Arrays;

/*
34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回[-1, -1]。

进阶：

你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 */
public class SearchFirstAndLastElement {
    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        // 二分查找左边界
        int first = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    first = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            }
        }

        // 二分查找右边界
        int last = -1;
        if (first != -1) {
            low = first;
            high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] > target) {
                    high = mid - 1;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    if (mid == n - 1 || nums[mid + 1] != target) {
                        last = mid;
                        break;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        } else {
            return new int[]{-1, -1};
        }
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2, 4, 5, 6};
        System.out.println(Arrays.toString(searchRange(nums, 6)));
    }
}
