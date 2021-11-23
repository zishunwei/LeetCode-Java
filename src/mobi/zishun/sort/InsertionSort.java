package mobi.zishun.sort;

import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;

        for (int i = 1; i < n; i++) {
            // 需要排序的元素
            int selected = nums[i];
            // 存储需要插入元素的索引
            int j = i - 1;
            //从尾到头遍历已排序区，插入元素
            for (; j >= 0; j--) {
                if (selected < nums[j]) {
                    // 已排序区对应元素往后移动一位
                    nums[j + 1] = nums[j];
                } else {
                    // 因为是已排序区，判断selected比已排序区任一值大即说明排序完成
                    break;
                }
            }
            // 插入修改元素-索引j位置(因为循环结束后还会j--一次)
            nums[j + 1] = selected;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 6, 5, 2, 3};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}