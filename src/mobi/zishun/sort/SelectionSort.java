package mobi.zishun.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] nums) {
        int n = nums.length;

        //逐步确定最小值（最左边）
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            int min = nums[minIndex];
            //从未排序区间中找到最小的元素
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            // 将其放到已排序区间的末尾
            int temp = nums[i];
            nums[i] = min;
            nums[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 6, 5, 2, 3};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


}