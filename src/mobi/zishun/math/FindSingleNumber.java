package mobi.zishun.math;

import java.util.HashSet;
import java.util.Set;

/*
 * 136. 只出现一次的数字
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
示例 1:
输入: [2,2,1]
输出: 1
* https://leetcode-cn.com/problems/single-number/
 */
public class FindSingleNumber {
    // 位运算 - 不使用额外空间
    public int singleNumber(int[] nums) {
        // 异或运算符是用符号“^”表示的，其运算规律是：
        // 两个操作数的位中，相同则结果为0，不同则结果为1。
        // 两个相同的数异或运算后为0 (中途有其它数加入异或运算也不影响)
        int res = 0;
        for (int num : nums){
            res ^= num;
        }
        return res;
    }

    // hashSet
    public int singleNumberV2(int[] nums) {
        Set<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums) {
            if (hashSet.contains(num)) {
                hashSet.remove(num);
            } else {
                hashSet.add(num);
            }
        }
        return hashSet.iterator().next();
    }
}
