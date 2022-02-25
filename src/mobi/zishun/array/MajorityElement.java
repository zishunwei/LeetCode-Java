package mobi.zishun.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
169. 多数元素

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class MajorityElement {
    // 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
    // Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        // 众数出现的次数肯定比其它所有元素加起来多
        // 一次遍历，遇见相同的数就将计数器+1，遇到不同的数-1
        // 相当于一个众数和一个其它数抵消了
        // 最后留下的数即为众数
        int count = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    // 哈希表
    public int majorityElementV2(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.replace(num, hashMap.get(num), hashMap.get(num) + 1);
            }
        }
        int freq = 0;
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > freq) {
                freq = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }

    // 排序
    public int majorityElementV3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
