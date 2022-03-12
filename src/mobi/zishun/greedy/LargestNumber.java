package mobi.zishun.greedy;

import java.util.Arrays;

/*
 * 179. 最大数
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
示例 1：
输入：nums = [10,2]
输出："210"
示例 2：
输入：nums = [3,30,34,5,9]
输出："9534330"
 */
public class LargestNumber {
    // 排序1 - String转换
    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换int成装箱对象，才可以使用Comparator
        String[] numsCopy = new String[n];
        for (int i = 0; i < n; i++) {
            numsCopy[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsCopy, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
//                return ba.compareTo(ab);
            // 从大到小排序，如果希望a在前，则返回b-a为负数
            return (int) (Long.parseLong(ba) - Long.parseLong(ab));
        });
        StringBuilder res = new StringBuilder();
        // 判断前缀有0的情况
        int i = 0;
        for (; i < n - 1; i++) {
            // 比较String不能用==
            if (!numsCopy[i].equals("0")) {
                break;
            }
        }
        // 只剩最后一位了，直接输出（有可能是全为0的情况）
        if (i == n - 1) {
            return numsCopy[n - 1];
        }
        // 接着输出非0位
        for (; i < n; i++) {
            res.append(numsCopy[i]);
        }
        return res.toString();
    }

    // 排序2 - int位传递排序 - 数学
    public String largestNumberV2(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            // 数学公式
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        LargestNumber m = new LargestNumber();
        int[] nums = {0, 0};
        System.out.println(m.largestNumber(nums));
    }
}
