package mobi.zishun.heap;

import java.util.PriorityQueue;

/*
 * 703. 数据流中的第 K 大元素
设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
请实现 KthLargest 类：
KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。

示例：
输入：
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
输出：
[null, 4, 5, 5, 8, 8]
 */
public class KthLargestElementInStream {
    // 小顶堆
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int k;

    // 我们可以使用一个大小为 k 的优先队列来存储前 k 大的元素，其中优先队列的队头为队列中最小的元素，也就是第 k 大的元素。
    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        for (int x : nums) {
            add(x);
        }
    }

    // 在单次插入的操作中，我们首先将元素 val 加入到优先队列中。
    // 如果此时优先队列的大小 k，我们需要将优先队列的队头元素弹出，以保证优先队列的大小为 k。
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.element();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        KthLargestElementInStream m = new KthLargestElementInStream(3, nums);
        System.out.println(m.add(3));
        System.out.println(m.add(5));
        System.out.println(m.add(10));
        System.out.println(m.add(9));
        System.out.println(m.add(4));
    }

}
