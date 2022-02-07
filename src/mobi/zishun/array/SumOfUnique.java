package mobi.zishun.array;

import java.util.HashMap;
import java.util.Map;

/*
 * 1748. 唯一元素的和
 * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 * 请你返回 nums 中唯一元素的 和 。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class SumOfUnique {
    public int sumOfUnique(int[] nums) {
        int[] countArr = new int[101];
        for (int num : nums) {
            countArr[num]++;
        }
        int res = 0;
        for (int i = 1; i <= 100; i++) {
            if (countArr[i] == 1) {
                res += i;
            }
        }
        return res;
    }

    // 哈希表，支持更大范围，开销更大
    public int sumOfUniqueV2(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> state = new HashMap<>();
        for (int num : nums) {
            if (!state.containsKey(num)) {
                ans += num;
                state.put(num, 1);
            } else if (state.get(num) == 1) {
                ans -= num;
                state.put(num, 2);
            }
        }
        return ans;
    }

}