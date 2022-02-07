package mobi.zishun.divideconquer;

import java.util.Arrays;

/*
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 */
public class ReversePairs {
    private int count; // 全局变量或者成员变量

    public int reversePairs(int[] nums) {
        count = 0;
        mergeSortCount(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSortCount(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortCount(nums, p, q);
        mergeSortCount(nums, q + 1, r);
        mergeCount(Arrays.copyOfRange(nums, p, q + 1), Arrays.copyOfRange(nums, q + 1, r + 1));
    }

    private void mergeCount(int[] nums1, int[] nums2) {
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 > num2) {
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        ReversePairs m = new ReversePairs();
        int[] nums = {2, 4, 3, 1, 5, 6};
//        int[] nums = {7, 5, 6, 4};
        System.out.println(m.reversePairs(nums));
    }

}
