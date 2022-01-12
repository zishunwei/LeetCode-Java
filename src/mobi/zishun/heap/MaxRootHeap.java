package mobi.zishun.heap;

public class MaxRootHeap {
    private int[] array; // 数组，从下标1开始存储数据
    private int n; // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public MaxRootHeap(int capacity) {
        // 索引0处不存值
        array = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    // 从下往上的堆化
    public void insert(int data) {
        if (count >= n) {
            return;
        }
        count++;
        array[count] = data;
        int i = count;
        // 注意：大小判断直接在循环条件中进行
        while (i > 0 && array[i] > array[i / 2]) {
            swap(array, i, i / 2);
            i /= 2;
        }
    }

    public int removeMax() {
        if (count == 0) {
            return -1;
        }
        int maxValue = array[1];
        array[1] = array[count];
        count--;

        heapify(array, count, 1);
        return maxValue;
    }

    // 从上往下的堆化
    private void heapify(int[] arr, int n, int i) {
        while (true){
            int maxPos = i;
            // 先和左子节点比较
            if (i * 2 <= n && arr[i] < arr[i * 2]) {
                maxPos = i * 2;
            }
            // 再和右子节点比较
            if (i * 2 + 1 <= n && arr[maxPos] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 如果以上两个判断都没有通过，说明已到底部
            if (maxPos == i){
                break;
            }
            // 交换位置
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
