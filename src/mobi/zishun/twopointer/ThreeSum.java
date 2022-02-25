package mobi.zishun.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
15. 三数之和
给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
https://blog.csdn.net/starflyyy/article/details/106955473
 */
public class ThreeSum {

    // 排序+双指针优化第三重循环
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        Arrays.sort(nums);
        // 对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
        for (int i = 0; i < n - 2; i++) {
            // 一层循环有重复，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第三重循环对应的指针
            int k = n - 1;
            for (int j = i + 1; j < n - 1; j++) {
                // 二层循环有重复，跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 优化第三重循环
                // 需要保证 b 的指针在 c 的指针的左侧
                while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                // 如果指针重合，随着 b 后续的增加（因为已排序）
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (j == k) {
                    break;
                }
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return res;
    }

    // 初版 - 暴力+排序去重 - O(N3) - 超出时间限制
    public List<List<Integer>> threeSumV2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        Arrays.sort(nums);
        // 对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
        for (int i = 0; i < n - 2; i++) {
            // 一层循环有重复，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 1; j++) {
                // 二层循环有重复，跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    // 三层循环有重复，跳过
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }

    // 排序+哈希表优化第三重循环 (更推荐双指针)
    public List<List<Integer>> threeSumV3(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        int n = nums.length;
        Arrays.sort(nums);
        // 对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
        for (int i = 0; i < n - 1; i++) {
            // 一层循环有重复，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            Map<Integer, Integer> hashMap = new HashMap<>();
            int target = -nums[i];
            for (int j = i + 1; j < n; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                    continue; // 如果连续三个元素相等, 则只有nums[j - 1]和nums[j - 2]是有用的
                }
                if (!hashMap.containsKey(nums[j])) {
                    hashMap.put(target - nums[j], j);
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[hashMap.get(nums[j])]));
                    // 更新符合条件的key, 当前的数之后还会用到
                    hashMap.remove(nums[j]); // 对于一个nums[i]和nums[j], 只可以用一次, 因此将其从hashmap移除, 后面没用了
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum m = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(m.threeSum(nums));
    }

}
