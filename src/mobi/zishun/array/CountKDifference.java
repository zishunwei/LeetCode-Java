package mobi.zishun.array;

import java.util.HashMap;
import java.util.Map;

/*
 * 2006. 差的绝对值为 K 的数对数目
 * 给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。
链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 */
public class CountKDifference {
    // 哈希表
    public int countKDifference(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            res += hashMap.getOrDefault(num + k, 0);
            res += hashMap.getOrDefault(num - k, 0);
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    // 计算排序思想 (时间性能最佳，仅适合数据大小不大的情况)
    //1 <= nums.length <= 200
    //1 <= nums[i] <= 100
    //1 <= k <= 99
    public int countKDifferenceV2(int[] nums, int k) {
        int res = 0;
        int[] freq = new int[101];
        for (int num : nums) {
            if (num + k <= 100) {
                res += freq[num + k];
            }
            if (num - k >= 0) {
                res += freq[num - k];
            }
            freq[num]++;
        }
        return res;
    }

    // 暴力枚举
    public int countKDifferenceV3(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    res++;
                }
            }
        }
        return res;
    }

}
