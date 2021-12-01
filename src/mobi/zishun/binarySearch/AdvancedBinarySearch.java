package mobi.zishun.binarySearch;

public class AdvancedBinarySearch {
    // 变体一：查找第一个值等于给定值的元素
    public static int binarySearchTheFirst(int[] nums, int value) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (value < nums[mid]) {
                high = mid - 1;
            } else if (value > nums[mid]) {
                low = mid + 1;
            } else {
                //如果 mid 等于 0，那这个元素已经是数组的第一个元素，那它肯定是我们要找的；
                // 如果 mid 不等于 0，但 a[mid]的前一个元素 a[mid-1]不等于 value，
                // 那也说明 a[mid]就是我们要找的第一个值等于给定值的元素。
                if (mid == 0 || nums[mid - 1] != value) {
                    return mid;
                } else {
                    // 如果经过检查之后发现 a[mid]前面的一个元素 a[mid-1]也等于 value，
                    // 那说明此时的 a[mid]肯定不是我们要查找的第一个值等于给定值的元素。
                    // 那我们就更新 high=mid-1，因为要找的元素肯定出现在[low, mid-1]之间。
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 4, 4, 4, 6, 7, 10, 11};
        System.out.println(binarySearchTheFirst(nums, 4));
    }

}