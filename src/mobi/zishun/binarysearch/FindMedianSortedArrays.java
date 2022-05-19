package mobi.zishun.binarysearch;

/*
 * 4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。
* 请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。
示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class FindMedianSortedArrays {
    // 二分查找 - O(log(m+n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    //  合并数组 - O(m+n)
    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int medium = (n1 + n2) / 2;

        int[] merged = new int[medium + 1];
        int i = 0;
        int j = 0;
        int count = 0;
        while (count < medium + 1) {
            if (i == n1) {
                merged[count] = nums2[j];
                j++;
            } else if (j == n2) {
                merged[count] = nums1[i];
                i++;
            } else if (nums1[i] <= nums2[j]) {
                merged[count] = nums1[i];
                i++;
            } else {
                merged[count] = nums2[j];
                j++;
            }
            count++;
        }

        if ((n1 + n2) % 2 == 1) {
            return merged[medium];
        } else {
            return (merged[medium] + merged[medium - 1]) * 0.5;
        }
    }

    public static void main(String[] args) {
        FindMedianSortedArrays m = new FindMedianSortedArrays();
        int[] nums1 = {1, 3, 4};
        int[] nums2 = {2, 5};
        System.out.println(m.findMedianSortedArrays(nums1, nums2));
    }
}
