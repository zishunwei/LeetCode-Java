package mobi.zishun.math;

/*
 * 287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

示例 1：
输入：nums = [1,3,4,2,2]
输出：2
示例 2：
输入：nums = [3,1,3,4,2]
输出：3
提示：
1 <= n <= 10^5
nums.length == n + 1
1 <= nums[i] <= n
nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
进阶：
如何证明 nums 中至少存在一个重复的数字?
你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
* https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class FindDuplicateNumber {
    // 二分查找
    // 因为数字都在 [1, n] 范围内（包括 1 和 n），数组长度和最大数字大小相同
    // 可以假设数组排序来理解，[1,2,2,3,4]，统计个数和mid比较
    // 小于等于mid的个数 <= mid; 说明重复数大于mid
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int low = 1;
        int high = n - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 记录小于等于mid的数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) { // 小于等于mid的个数 <= mid; 说明重复数大于mid
                low = mid + 1;
            } else { // 重复数小于等于mid
                high = mid;
            }
        }
        return high;
    }

    // 快慢指针 - O(n)
    // 我们对 nums 数组建图，每个位置 i 连一条 i→nums[i] 的边。
    // 由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，
    // 且我们要找到的 target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
    //  [1,3,4,2,2] 链表（i→nums[i]）为：0->1->3->2<->4，需要找到环的起点
    public int findDuplicateV2(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 此时，相遇点到环起点的步数 == 起点到环起点的步数
        int start = 0; // 起点
        while (start != slow){ // slow为相遇点
            slow = nums[slow];
            start = nums[start];
        }
        // 环的起点即为答案
        return slow;
    }
}