package mobi.zishun.sort;

import java.util.Arrays;

/*
 * 归并排序
 */
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
        mergeSortRecursion(nums, p, q);
        mergeSortRecursion(nums, q + 1, r);
        merge(nums, p, q, r);
    }

    // 合并两个排好序的数组
    // nums为原完整数组；在原数组相同位置合并nums(p...q)和nums(q+1...r)
    private static void merge(int[] nums, int p, int q, int r) {
        int i = p;
        int j = q + 1;

        int cur;
        int count = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q || j <= r) {
            if (i > q) {
                cur = nums[j];
                j++;
            } else if (j > r) {
                cur = nums[i];
                i++;
            } else if (nums[i] <= nums[j]) {
                cur = nums[i];
                i++;
            } else {
                cur = nums[j];
                j++;
            }
            temp[count] = cur;
            count++;
        }
        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r - p; ++i) {
            nums[p + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 1, 2, 3};
        mergeSort(nums, 6);
//        merge(nums, 0,1,3);
        System.out.println(Arrays.toString(nums));

    }
}