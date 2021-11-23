package mobi.zishun.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag =false;
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                    flag = true; //有数据交换
                }
            }
            // 上个遍历没有数据交换，说明已完成排序，退出循环
            if (!flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 1, 2, 3};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
