package mobi.zishun.sort;

import java.util.Arrays;

/*
 计数排序-桶排序的特殊情况
 */
public class CountingSort {
    public static int[] countingSort(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return nums;
        }
        // 查找数组中数据的范围
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        // 计数数组c
        int[] c = new int[max - min + 1];
        for (int num : nums) {
            c[num - min]++;
        }

        // c数组顺序求和
        int sum = c[0];
        for (int i = 1; i < max - min + 1; i++) {
            sum += c[i];
            c[i] = sum;
        }

        // 倒序遍历c数组，输出结果数组
        int[] res = new int[n];
        for (int num : nums) {
            // num的排序后索引值为c[num]-1
            res[c[num - min] - 1] = num;
            c[num - min]--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 5, 3, 6, 2, -1,-1,0,-2,0};
        System.out.println(Arrays.toString(countingSort(nums)));
    }


}