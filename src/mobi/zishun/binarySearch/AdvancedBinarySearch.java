package mobi.zishun.binarySearch;

public class AdvancedBinarySearch {
    // 变体一：查找第一个值等于给定值的元素
    public static int binarySearchTheFirst(int[] nums, int value) {
        int n = nums.length;
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

    // 变体二：查找最后一个值等于给定值的元素
    public static int binarySearchTheLast(int[] nums, int value) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (value < nums[mid]) {
                high = mid - 1;
            } else if (value > nums[mid]) {
                low = mid + 1;
            } else {
                if (mid == n - 1) {
                    return mid;
                } else if (nums[mid + 1] != value) {
                    return mid;
                }
                {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    //变体三：查找第一个大于等于给定值的元素
    public static int searchTheFisrtGreaterValue(int[] nums, int value) {
        int n = nums.length;
        if (value > nums[n - 1]) {
            return -1;
        }
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (value > nums[mid]) {
                low = mid + 1;
            } else {
                // 注意mid为第一位的情况，mid==0
                if (mid == 0 || value > nums[mid - 1]) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //变体四：查找最后一个小于等于给定值的元素
    public static int searchTheLastLessValue(int[] nums, int value) {
        int n = nums.length;
        return recursion(nums, value, 0, n - 1);
    }

    private static int recursion(int[] nums, int value, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (nums[mid] > value) {
            return recursion(nums, value, low, mid - 1);
        } else {
            if (mid == high || nums[mid + 1] > value) {
                return mid;
            } else {
                return recursion(nums, value, mid + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 4, 4, 4, 6, 7, 10, 11, 11, 12};
        System.out.println(nums.length);
        System.out.println(binarySearchTheFirst(nums, 4));
        System.out.println(binarySearchTheLast(nums, 11));

        System.out.println(searchTheFisrtGreaterValue(nums, 1));
        System.out.println(searchTheLastLessValue(nums, 2));
    }

}