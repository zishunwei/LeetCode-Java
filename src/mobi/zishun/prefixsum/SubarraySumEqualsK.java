package mobi.zishun.prefixsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * 560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

示例 1：
输入：nums = [1,1,1], k = 2
输出：2
示例 2：
输入：nums = [1,2,3], k = 3
输出：2
提示：
1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7
* https://leetcode-cn.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {
    // 哈希表+前缀和 - 精简版(记录前缀和出现个数) - 边生成前缀和边查询满足条件的个数
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        // key:前缀和; value:当前前缀和出现的个数
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);
        int prefix = 0;
        for (int num : nums) {
            prefix += num;
            if (prefixMap.containsKey(prefix - k)) {
                res += prefixMap.get(prefix - k);
            }
            prefixMap.put(prefix, prefixMap.getOrDefault(prefix, 0) + 1);
        }
        return res;
    }

    // 初版 - 哈希表+前缀和 - 循环两次(比较索引前后) - 代码冗余 - 复杂度高
    public int subarraySumV2(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        HashMap<Integer, List<Integer>> prefixMap = new LinkedHashMap<>();
        prefix[0] = nums[0];
        prefixMap.put(prefix[0], new ArrayList<>(Arrays.asList(0)));
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
            List<Integer> indexList = prefixMap.getOrDefault(prefix[i], new ArrayList<>());
            indexList.add(i);
            prefixMap.put(prefix[i], indexList);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (prefix[i] == k) {
                res++;
            }
            if (prefixMap.containsKey(prefix[i] - k)) {
                List<Integer> indexList = prefixMap.get(prefix[i] - k);
                for (int index : indexList) {
                    if (index < i) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK m = new SubarraySumEqualsK();
        int[] nums = {1, 2, 3};
        int k = 3;
        System.out.println(m.subarraySum(nums, k));
    }
}
