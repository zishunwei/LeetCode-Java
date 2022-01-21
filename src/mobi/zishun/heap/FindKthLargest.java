package mobi.zishun.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 215. 数组中的第K个最大元素
 */
public class FindKthLargest {
    // 堆排序
    // 大小顶堆的方案虽然都是堆，但是思路是完全不同的。
    // 大顶堆是典型的排序思路（即堆排序），建堆后，逐个pick堆顶元素，就能获得全序数组；
    // 小顶堆则是利用堆的偏序性质，因为我们并不需要全序数组，所以“第k大”这样的偏序元素可以通过小根堆堆顶来直接确定，
    // 然而比较可惜的是其仍然达不到quick-select的O(n)。
    // 不过大小顶堆的平均时间复杂度实际是一样的，除非有特别限制，诸如“k远小于n”等；
    // 而空间复杂度上，因为题目是原地建堆，只有递归栈消耗，和小顶堆方案的O(k)比，多数情况下还是会更优一些。
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < 1 && k > nums.length) {
            return -1;
        }

        // 原地建堆
        buildMaxHeap(nums, nums.length);

        //建堆完毕后，nums【0】为最大元素。逐个删除堆顶元素，直到删除了k-1个。
        for (int i = nums.length - 1; i > nums.length - k; i--) {
            nums[0] = nums[i];
//            swap(nums, 0, i);
            maxHeapify(nums, i, 0);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, heapSize, i);
        }
    }

    // 从上往下堆化
    // heapSize决定arr前多少位为堆
    // i为需要进行堆化的索引
    private void maxHeapify(int[] arr, int heapSize, int i) {
        int maxPos = i;
        if (i * 2 + 1 < heapSize && arr[maxPos] < arr[i * 2 + 1]) {
            maxPos = i * 2 + 1;
        }
        if (i * 2 + 2 < heapSize && arr[maxPos] < arr[i * 2 + 2]) {
            maxPos = i * 2 + 2;
        }
        // 递归方法-非while循环
        if (maxPos != i) {
            swap(arr, i, maxPos);
            //由于交换了父节点和子节点，因此可能对子节点的子树造成影响，所以对子节点的子树进行调整。
            maxHeapify(arr, heapSize, maxPos);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int findKthLargestByPriorityQueue(int[] nums, int k) {
        if (nums.length < 1 && k > nums.length) {
            return -1;
        }
        Queue<Integer> queue = new PriorityQueue<>(k, (a, b) -> (b - a));
        for (int num : nums) {
            queue.add(num);
        }
        for (int i = 0; i < k - 1; i++) {
            queue.remove();
        }
        return queue.remove();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(10, (a, b) -> (b - a));
        for (int i = 0; i < 10; i++) {
            queue.add((int) (Math.random() * 100));
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }

}
