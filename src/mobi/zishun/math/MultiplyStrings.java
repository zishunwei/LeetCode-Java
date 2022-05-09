package mobi.zishun.math;

/*
 * 43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
示例 1:
输入: num1 = "123", num2 = "456"
输出: "56088"
提示：
1 <= num1.length, num2.length <= 200
num1 和 num2 只能由数字组成。
num1 和 num2 都不包含任何前导零，除了数字0本身。
* https://leetcode-cn.com/problems/multiply-strings/
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";
        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int flag = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            // num2当前的数
            int cur2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 cur2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || flag != 0; j--) {
                int cur1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (cur1 * cur2 + flag) % 10;
                temp.append(product);
                flag = (cur1 * cur2 + flag) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    // 与415. 字符串相加相同
    private String addStrings(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int i = m - 1;
        int j = n - 1;
        // 保存进位的数
        int flag = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int cur1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int cur2 = j >= 0 ? num2.charAt(j) - '0': 0;
            int curSum = cur1 + cur2 + flag;
            flag = curSum / 10;
            res.append(curSum % 10);
            i--;
            j--;
        }
        if (flag > 0){
            res.append('1');
        }
        return res.reverse().toString();
    }

}
