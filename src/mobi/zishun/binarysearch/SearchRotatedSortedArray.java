package mobi.zishun.binarysearch;

/*
33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。

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
    // O(log(n))版本 - 递归版本
    // 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
    // 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
    public int search(int[] nums, int target) {
        search(nums, target, 0, nums.length - 1);
        return res;
    }

    int res = -1;

    private void search(int[] nums, int target, int left, int right) {
        if (left > right || res > -1) {
            return;
        }
        int medium = (left + right) >> 1;
        if (nums[left] <= nums[medium]) {
            binarySearch(nums, target, left, medium);
        } else {
            search(nums, target, left, medium);
        }
        if (medium < right) {
            if (nums[medium + 1] <= nums[right]) {
                binarySearch(nums, target, medium + 1, right);
            } else {
                search(nums, target, medium + 1, right);
            }
        }
    }

    private void binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int medium = (right + left) >> 1;
            if (nums[medium] == target) {
                res = medium;
                return;
            }
            if (nums[medium] > target) {
                right = medium - 1;
            } else {
                left = medium + 1;
            }
        }
    }

    // O(log(n))版本 - 循环版本
    public int searchV2(int[] nums, int target) {
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
            if (nums[mid] == target) {
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
        SearchRotatedSortedArray m = new SearchRotatedSortedArray();
        int[] nums = {4, 5, 6, 1};
        System.out.println(m.search(nums, 1));
        System.out.println(m.searchV2(nums, 1));

    }

}
