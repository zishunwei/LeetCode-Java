package mobi.zishun.divideconquer;

/*
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 */
public class ReversePairs {
    private int res; // 全局变量或者成员变量

    public int reversePairs(int[] nums) {
        res = 0;
        mergeSortCount(nums, 0, nums.length - 1);
        return res;
    }

    private void mergeSortCount(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortCount(nums, p, q);
        mergeSortCount(nums, q + 1, r);
        mergeCount(nums, p, q, r);
        // 初版本-复杂度高
//        mergeCount(Arrays.copyOfRange(nums, p, q + 1), Arrays.copyOfRange(nums, q + 1, r + 1));
    }

    // nums为原完整数组；计算nums[p...q]和nums[q+1...r]的逆序对
    // 同时将nums[p...r]进行归并排序
    // 数组递归到最终里面只有一个元素的时候，两个一个元素的数组归并的情形，这时候，一个元素肯定是有序的，
    // 两个有序的数组合并，就是上面说的情形了，再合并，就是多个有序元素的数组的合并了
    private void mergeCount(int[] nums, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int count = 0;

        int[] temp = new int[r - p + 1];
        int cur;

        while (i <= q || j <= r) {
            if (i > q) {
                cur = nums[j];
                j++;
            } else if (j > r) {
                cur = nums[i];
                i++;
            } else if (nums[i] > nums[j]) {
                // 统计nums[p...q]之间，比nums[j]大的元素个数
                // 此时nums[p...q]中i索引之后的元素都比nums[j]大（因为nums[p...q]已完成归并排序）
                res += q - i + 1;
                cur = nums[j];
                j++;
            } else {
                cur = nums[i];
                i++;
            }
            temp[count] = cur;
            count++;
        }

        for (int k = 0; k < r - p + 1; k++) {
            nums[p + k] = temp[k];
        }
    }

    // 初版本-复杂度高
//    private void mergeCount(int[] nums1, int[] nums2) {
//        for (int num1 : nums1) {
//            for (int num2 : nums2) {
//                if (num1 > num2) {
//                    count++;
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        ReversePairs m = new ReversePairs();
        int[] nums = {2, 4, 3, 1, 5, 6}; // 4
        int[] nums2 = {7, 5, 6, 4}; // 5
        System.out.println(m.reversePairs(nums));
        System.out.println(m.reversePairs(nums2));
    }

}
