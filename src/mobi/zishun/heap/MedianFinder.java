package mobi.zishun.heap;

import java.util.PriorityQueue;

public class MedianFinder {
    // heapMin记录小于中位数的数（大顶堆）
    // heapMax记录大于中位数的数（小顶堆）
    private final PriorityQueue<Integer> heapMin;
    private final PriorityQueue<Integer> heapMax;

    public MedianFinder() {
        heapMin = new PriorityQueue<>((a, b) -> (b - a));
        heapMax = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 优先添加元素到大顶堆
        // 如果总数为奇数，大顶堆个数比小顶堆多一
        if (heapMin.isEmpty() || num <= heapMin.element()) {
            heapMin.add(num);
            // 大顶堆个数减小顶堆大于1了，调整
            if (heapMin.size() > heapMax.size() + 1) {
                heapMax.add(heapMin.remove());
            }
        } else {
            heapMax.add(num);
            // 大顶堆个数比小顶堆少了，调整
            if (heapMax.size() > heapMin.size()) {
                heapMin.add(heapMax.remove());
            }
        }
    }

    public double findMedian() {
        if (heapMin.size() == heapMax.size()) {
            return (heapMax.element() + heapMin.element()) / 2.0;
        } else {
            return (double) heapMin.element();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        int[] nums = {1, 4, 3, 2, 1};
        for (int num : nums) {
            medianFinder.addNum(num);
            System.out.println(medianFinder.findMedian());
        }
    }
}
