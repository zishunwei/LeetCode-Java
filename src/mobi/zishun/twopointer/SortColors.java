package mobi.zishun.twopointer;

import java.util.Arrays;

/*
 * 75. 颜色分类
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
必须在不使用库的sort函数的情况下解决这个问题。
示例 1
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
提示：
n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
进阶：
你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？
* https://leetcode-cn.com/problems/sort-colors/
 */
public class SortColors {
    public void sortColors(int[] nums) {
        // red代表红色将要交换的位置（最靠后）
        // white代表红色将要交换的位置（最靠后）
        int red = 0;
        int white = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (nums[red] == 1){
                    swap(nums, i, red);
                    red++;
                    // 0和1进行交换后，需要再对1进行交换
                    swap(nums, i, white);
                    white++;
                } else { //当前是0和2进行交换
                    swap(nums, i, red);
                    // 直接将0、1的位置全部后移即可
                    red++;
                    white++;
                }
            } else if (nums[i] == 1) {
                swap(nums, i, white);
                white++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortColors m = new SortColors();
        int[] nums = {2,0,2,1,1,0};
        m.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

}
