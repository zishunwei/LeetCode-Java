package mobi.zishun.math;

/*
 * 258. 各位相加
给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
输入: num = 38
输出: 2
解释: 各位相加的过程为：
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2
由于2 是一位数，所以返回 2。
链接：https://leetcode-cn.com/problems/add-digits
 */
public class AddDigits {
    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                // 从个位向前
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    // 求数根 - 数学公式 - O(1)
    // num 不是 9 的倍数时，其数根即为 num 除以 9 的余数。
    // num 是 9 的倍数时：
    //   如果 num=0，则其数根是 0；
    //   如果 num>0，则各位相加的结果大于 0，其数根也大于 0，因此其数根是 9。
    public int addDigitsV2(int num) {
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        AddDigits m = new AddDigits();
        System.out.println(m.addDigits(38));
    }

}
