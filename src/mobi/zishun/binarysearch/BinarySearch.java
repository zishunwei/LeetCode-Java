package mobi.zishun.binarysearch;

public class BinarySearch {
    public static int binarySearch(int[] nums, int value) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        // 注意是 low<=high，而不是 low < high
        // 偶数个数、查找最末尾的数会导致最后low==high
        while (low <= high) {
            //mid=(low+high)/2 这种写法是有问题的。
            // 因为如果 low 和 high 比较大的话，两者之和就有可能会溢出。
            // 改进的方法是将 mid 的计算方式写成 low+(high-low)/2
            int mid = low + (high - low) / 2;
            if (nums[mid] == value) {
                return mid;
            } else if (value < nums[mid]) {
                high = mid - 1;
            } else if (value > nums[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearchByRecursion(int[] nums, int value) {
        int n = nums.length;
        return recursion(nums, 0, n - 1, value);
    }

    private static int recursion(int[] nums, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (nums[mid] == value) {
            return mid;
        } else if (value < nums[mid]) {
            return recursion(nums, low, mid - 1, value);
        } else {
            return recursion(nums, mid + 1, high, value);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 4, 4, 4, 6, 7, 10, 11};
        System.out.println(binarySearchByRecursion(nums, 6));
    }
}
