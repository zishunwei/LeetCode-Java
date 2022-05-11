package mobi.zishun.array;

/*
 * 88. 合并两个有序数组
给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
* 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
* https://leetcode.cn/problems/merge-sorted-array/
 */
public class MergeTwoArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int[] sorted = new int[m + n];
        int cur;
        int count = 0;
        while (i < m || j < n) {
            if (i==m){
                cur = nums2[j];
                j++;
            } else if (j==n){
                cur = nums1[i];
                i++;
            }
            else if (nums1[i] <= nums2[j]){
                cur = nums1[i];
                i++;
            } else {
                cur = nums2[j];
                j++;
            }
            sorted[count] = cur;
            count++;
        }
        System.arraycopy(sorted, 0, nums1, 0, m + n);
    }
}