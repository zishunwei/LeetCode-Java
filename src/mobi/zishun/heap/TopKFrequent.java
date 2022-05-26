package mobi.zishun.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 347. 前 K 个高频元素
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1 );
        }

        int[][] twoDimensionArr = new int[hashMap.size()][2];
        int count = 0;
        for (Map.Entry<Integer, Integer> kv : hashMap.entrySet()) {
            // 第一位为num,第二位为出现频次
            int[] arr = new int[2];
            arr[0] = kv.getKey();
            arr[1] = kv.getValue();
            twoDimensionArr[count] = arr;
            count++;
        }

        buildMaxHeap(twoDimensionArr);

        int[] result = new int[k];
        count = 0;
        for (int i = twoDimensionArr.length - 1; i >= twoDimensionArr.length - k; i--) {
            result[count] = twoDimensionArr[0][0];
            count++;
            twoDimensionArr[0] = twoDimensionArr[i];
            maxHeapify(twoDimensionArr, i, 0);
        }
        return result;
    }

    private void buildMaxHeap(int[][] twoDimensionArr) {
        for (int i = twoDimensionArr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(twoDimensionArr, twoDimensionArr.length, i);
        }
    }

    private void maxHeapify(int[][] twoDimensionArr, int heapSize, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 + 1 < heapSize && twoDimensionArr[i][1] < twoDimensionArr[i * 2 + 1][1]) {
                maxPos = i * 2 + 1;
            }
            if (i * 2 + 2 < heapSize && twoDimensionArr[maxPos][1] < twoDimensionArr[i * 2 + 2][1]) {
                maxPos = i * 2 + 2;
            }
            if (i == maxPos) {
                break;
            }
            swap(twoDimensionArr, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[][] twoDimensionArr, int i, int j) {
        int[] temp = twoDimensionArr[i];
        twoDimensionArr[i] = twoDimensionArr[j];
        twoDimensionArr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 4, 2, 1, 2, 3, 4, 4, 4};
        int k = 2;
        TopKFrequent method = new TopKFrequent();
        System.out.println(Arrays.toString(method.topKFrequent(nums, k)));
//        int[][] twoDimensionArr = new int[3][2];
//        System.out.println(Arrays.deepToString(twoDimensionArr));
    }
}
