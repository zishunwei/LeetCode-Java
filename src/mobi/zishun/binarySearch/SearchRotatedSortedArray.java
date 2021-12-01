package mobi.zishun.binarySearch;

/*
33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4

示例2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1

示例 3：
输入：nums = [1], target = 0
输出：-1
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int rotatedIndex = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                rotatedIndex = i;
            }
        }
        int low = 0;
        int high = n - 1;
        if (target < nums[0]) {
            low = rotatedIndex + 1;
        } else {
            high = rotatedIndex;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    // O(log(n))版本 - 递归版本
    public static int searchV2(int[] nums, int target) {
        int n = nums.length;
        return searchV2Recursion(nums, target, 0, n - 1);
    }

    private static int searchV2Recursion(int[] nums, int target, int low, int high) {
        int mid = low + (high - low) / 2;

        if (low > high) {
            return -1;
        }

        // 前半段有序
        if (nums[mid] >= nums[low]) {
            // 前半段有序+target在前半段
            if (target <= nums[mid] && target >= nums[low]) {
                high = mid;
                while (low <= high) {
                    mid = low + (high - low) / 2;
                    if (nums[mid] == target) {
                        return mid;
                    } else if (target < nums[mid]) {
                        high = mid - 1;
                    } else if (target > nums[mid]) {
                        low = mid + 1;
                    }
                }
                return -1;
                // 前半段有序+target不在前半段
            } else {
                return searchV2Recursion(nums, target, mid + 1, high);
            }
            //后半段有序
        } else {
            // 后半段有序+target在后半段
            if (target >= nums[mid + 1] && target <= nums[high]) {
                low = mid + 1;
                while (low <= high) {
                    mid = low + (high - low) / 2;
                    if (nums[mid] == target) {
                        return mid;
                    } else if (target < nums[mid]) {
                        high = mid - 1;
                    } else if (target > nums[mid]) {
                        low = mid + 1;
                    }
                }
                return -1;
                // 后半段有序+target不在后半段
            } else {
                return searchV2Recursion(nums, target, low, mid);
            }
        }
    }

    // O(log(n))版本 - 循环版本
    public static int searchV3(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] >= nums[low]) {
                if (target <= nums[mid] && target >= nums[low]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target <= nums[high] && target >= nums[mid + 1]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {4,5,6, 1};
        System.out.println(search(nums, 1));
        System.out.println(searchV2(nums, 2));
        System.out.println(searchV3(nums, 1));

    }

}
