package mobi.zishun.sort;

import java.util.LinkedList;
import java.util.List;

/*
 * 969. 煎饼排序
给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
一次煎饼翻转的执行过程如下：
选择一个整数 k ，1 <= k <= arr.length
反转子数组 arr[0...k-1]（下标从 0 开始）
例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
示例 1：
输入：[3,2,4,1]
输出：[4,2,4,3]
解释：
我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
初始状态 arr = [3, 2, 4, 1]
第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
链接：https://leetcode-cn.com/problems/pancake-sorting
 */
public class PancakeSort {
    private final List<Integer> result = new LinkedList<>();

    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        recursion(arr, n);
        return result;
    }

    private void recursion(int[] arr, int end) {
        if (end == 1) {
            return;
        }
        // 找到0到end的最大值索引+1
        int max = findMax(arr, end);
        // 按max位置翻转arr[0...max]
        result.add(max);
        reverseArr(arr, max);
        // 按end位置翻转arr[0...end]
        result.add(end);
        reverseArr(arr, end);
        // 此时end位置对应的是最大值，递归处理arr[0...end-1]
        recursion(arr, --end);
    }

    private void reverseArr(int[] arr, int end) {
        for (int i = 0; i < end / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[end - i - 1];
            arr[end - i - 1] = temp;
        }
    }

    private int findMax(int[] arr, int end) {
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < end; i++) {
            if (arr[i] >= max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1};
        PancakeSort m = new PancakeSort();
        System.out.println(m.pancakeSort(arr));
    }

}
