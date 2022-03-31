package mobi.zishun.math;

import java.util.LinkedList;
import java.util.List;

/*
 * 728. 自除数
自除数 是指可以被它包含的每一位数整除的数。
例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
自除数 不允许包含 0 。
给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。

示例 1：
输入：left = 1, right = 22
输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
示例 2:
输入：left = 47, right = 85
输出：[48,55,66,77]
提示：
1 <= left <= right <= 104
* https://leetcode-cn.com/problems/self-dividing-numbers/
 */
public class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new LinkedList<>();
        for (int num = left; num <= right; num++) {
            if (isAvailable(num)) {
                result.add(num);
            }
        }
        return result;
    }

    private boolean isAvailable(int num) {
        int copy = num;
        while (copy > 0) {
            int curDigit = copy % 10;
            if (curDigit == 0 || num % curDigit != 0) {
                return false;
            }
            copy /= 10;
        }
        return true;
    }

    public static void main(String[] args){
        SelfDividingNumbers m = new SelfDividingNumbers();
        System.out.println( 1 % 10);
        System.out.println(m.selfDividingNumbers(20,20));
    }

}
