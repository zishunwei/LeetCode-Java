package mobi.zishun.binarysearch;

/*
 * 540. 有序数组中的单一元素
给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
请你找出并返回只出现一次的那个数。
你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
* 输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 */
public class SingleNonDuplicate {
    // 二分查找
    public int singleNonDuplicateV2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
//            考虑 mid 和 1 按位与运算的结果，其中 & 是按位与运算符：
//            当 mid 是偶数时，mid & 1 = 0；
//            当 mid 是奇数时，mid & 1 = 1。
            mid -= (mid & 1);
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    // 初版-二分查找-代码较冗余
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int medium = (high - low) / 2 + low;
            if (medium == 0 && nums[medium] != nums[medium + 1]) {
                return nums[medium];
            } else if (medium == n - 1 && nums[medium] != nums[medium - 1]) {
                return nums[medium];
            } else if (nums[medium] != nums[medium - 1] && nums[medium] != nums[medium + 1]) {
                return nums[medium];
            } else if (nums[medium] == nums[medium + 1] && medium % 2 == 0) {
                low = medium + 1;
            } else if (nums[medium] == nums[medium - 1] && medium % 2 == 1) {
                low = medium + 1;
            } else if (nums[medium] == nums[medium + 1] && medium % 2 == 1) {
                high = medium - 1;
            } else if (nums[medium] == nums[medium - 1] && medium % 2 == 0) {
                high = medium - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a = 3;
        a -= 2 & 1;
        System.out.println(a);

//        SingleNonDuplicate m = new SingleNonDuplicate();
//        int[] nums = {1, 2, 2};
//        System.out.println(m.singleNonDuplicate(nums));
    }

}
