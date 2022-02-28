package mobi.zishun.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class SlidingWindowMaximum {
    // 双端队列 - O(n)
    // 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j，
    // 其中 i 在 j 的左侧（i < j），并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]），那么会发生什么呢？
    // 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。
    // 因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除。
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 双端队列存储下标索引
        Deque<Integer> deque = new LinkedList<>();
        // 初始化双端队列（第一个窗口）
        for (int i = 0; i < k; i++) {
            // 队列中保留当前最大值和其后面的元素索引
            // 最大值索引保持在队首
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        // 输出第一个窗口的结果
        int[] res = new int[n - k + 1];
        res[0] = nums[deque.peekFirst()];
        // 遍历数组将后续元素入队
        for (int i = k; i < n; i++) {
            // 队列中同样保留当前最大值和其后面的元素
            // 最大值保持在队首
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // 保证队首的最大值索引还在滑动窗口内
            while (deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            res[i - k + 1] = nums[deque.peekFirst()];
        }
        return res;
    }

    // 优先队列 - O(nlogn)
    public int[] maxSlidingWindowV2(int[] nums, int k) {
        int n = nums.length;
        // 优先队列存有两个数字的数组，第一个为nums元素值，第二个为此元素下标索引
        Queue<int[]> priorityQueue = new PriorityQueue<>((pair1, pair2) -> {
            // 保证最大值在队首
            // 如果有多个最大值，则保证索引最大(位置靠后)的那个在队首
            if (pair1[0] != pair2[0]) {
                return pair2[0] - pair1[0];
            } else {
                return pair2[1] - pair1[1];
            }
        });
        // 初始化优先队列（第一个窗口）
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new int[]{nums[i], i});
        }
        // 输出第一个窗口的结果
        int[] res = new int[n - k + 1];
        res[0] = priorityQueue.peek()[0];
        // 遍历数组添加后续元素
        for (int i = k; i < n; i++) {
            // 当前元素入队
            priorityQueue.add(new int[]{nums[i], i});
            // 如果队首元素的索引已经出了滑动窗口左边界，循环出队
            // 保证队首元素在当前滑动窗口中
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.remove();
            }
            // 输出当前滑动窗口的结果
            res[i - k + 1] = priorityQueue.peek()[0];
        }
        return res;
    }

    // 暴力法 - O(n*k) - 超出时间限制
    public int[] maxSlidingWindowV3(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > curMax) {
                    curMax = nums[j];
                }
            }
            res[i] = curMax;
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum m = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(m.maxSlidingWindow(nums, 3)));
    }

}
