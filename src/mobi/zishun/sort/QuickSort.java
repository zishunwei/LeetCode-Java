package mobi.zishun.sort;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] nums) {
        int n = nums.length;
        recursion(nums, 0, n - 1);
    }

    private static void recursion(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(nums, p, r);
        // 注意！！是q-1不是q!
        // 第一次分区后-q即时中间的元素了
        recursion(nums, p, q - 1);
        recursion(nums, q + 1, r);
    }

    // 分区，选定q，原地移动nums中元素
    private static int partition(int[] nums, int p, int q) {
        int pivot = nums[q];
        //游标i对应的是什么数字并不关心，它只是一个分界线，游标i前面的区间都是小于pivot的
        int i = p;
        for (int j = p; j < q; j++) {
            if (nums[j] < pivot) {
                if (i != j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                i++;
            }
        }
        nums[q] = nums[i];
        nums[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 5, 3, 6};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));

        int pivot = partition(nums, 0, 5);
        System.out.println(pivot);
        System.out.println(Arrays.toString(nums));
    }

}