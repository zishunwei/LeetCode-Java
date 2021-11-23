package mobi.zishun.array;

public class MergeTwoArrays {
    public void mergeTwoArrays(int[] nums1, int m, int[] nums2, int n) {
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