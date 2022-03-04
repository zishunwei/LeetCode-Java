package mobi.zishun.twopointer;

import java.util.Arrays;

/*
 * 189. 轮转数组
给你一个数组，将数组中的元素向右轮转 k个位置，其中k是非负数。
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
* 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
* 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
链接：https://leetcode-cn.com/problems/rotate-array
 */
public class RotateArray {
    // 1. 临时数组存储 + 拼接
    // 2. 新建结果数组 - 按索引重新赋值
    // 3. 环状替换 - 空间复杂度 O(1）
    // 4. 数组翻转 - 空间复杂度 O(1）

    // 方法二：环状替换
    // 也可以使用另外一种方式完成代码：使用单独的变量count 跟踪当前已经访问的元素数量，
    // 当 count=n 时，结束遍历过程。
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int count = 0; // 记录交换位置的次数，n个同学一共需要换n次
        int start = 0; // 从0位置开始换位子
        while (count < len) {
            int curIndex = start; // 从0位置开始换位子
            int preNum = nums[curIndex];
            do {
                int next = (curIndex + k) % len; // 为当前元素最终应该在的位置
                int temp = nums[next];    // 将被替换的元素来到角落...
                nums[next] = preNum; // 当前元素来到最终位置
                preNum = temp; // 角落的元素记录在preNum
                curIndex = next; // 当前索引记录更新，后续接着此索引向后循环推移
                count++; // 确定好的元素计数+1
            } while (start != curIndex); // 循环暂停，回到起始位置，角落无人
            start++; // 处理下一个位置的元素
        }
    }

    // 数组翻转
    public void rotateV2(int[] nums, int k) {
        int n = nums.length;
        // 需要轮转的次数（轮转n的倍数次时恢复原样）
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 暴力法 - 超出时间限制 - O(k*n)
    public void rotateV3(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k %= n;
        while (k > 0) {
            for (int i = n - 1; i > 0; i--) {
                swap(nums, i, i - 1);
            }
            k--;
        }
    }

    public static void main(String[] args) {
//        System.out.println(13 % 5);
        RotateArray m = new RotateArray();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        m.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }


}
