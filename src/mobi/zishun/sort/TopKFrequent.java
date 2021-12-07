package mobi.zishun.sort;

import java.util.Arrays;

/*
347. 前 K 个高频元素
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class TopKFrequent {
    //计数排序，时间复杂度好（O[n+m]）,空间复杂度高
    public static int[] topKFrequent(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int countArrLen = max - min + 1;
        int[] countArr = new int[countArrLen];
        for (int num : nums) {
            countArr[num - min]++;
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            int maxCount = countArr[0];
            int maxIndex = 0;
            for (int j = 1; j < countArrLen; j++) {
                if (countArr[j] > maxCount) {
                    maxCount = countArr[j];
                    maxIndex = j;
                }
            }
            res[i] = maxIndex + min;
            countArr[maxIndex] = 0;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 4, 4, 4, 4, 3, 3, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 3)));
    }

}
