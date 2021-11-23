package mobi.zishun.sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] nums, int n) {
        mergeSortRecursion(nums, 0, n - 1);
    }

    private static void mergeSortRecursion(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        // 以下三个操作都是在原nums引用地址上进行修改
        mergeSortRecursion(nums, 0, q);
        mergeSortRecursion(nums, q + 1, r);
        merge(nums, Arrays.copyOfRange(nums, 0, q + 1), Arrays.copyOfRange(nums, q + 1, r + 1));
    }

    // 合并两个排好序的数组
    private static void merge(int[] nums, int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        int count = 0;
        int cur;
        while (i < nums1.length || j < nums2.length) {
            if (i == nums1.length) {
                cur = nums2[j];
                j++;
            } else if (j == nums2.length) {
                cur = nums1[i];
                i++;
            } else if (nums1[i] <= nums2[j]) {
                cur = nums1[i];
                i++;
            } else {
                cur = nums2[j];
                j++;
            }
            nums[count] = cur;
            count++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 1, 2, 3};
        mergeSort(nums, 6);
        System.out.println(Arrays.toString(nums));
    }
}