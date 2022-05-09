package mobi.zishun.math;

/*
 * 415. 字符串相加
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。

示例 1：
输入：num1 = "11", num2 = "123"
输出："134"
示例 2：
输入：num1 = "456", num2 = "77"
输出："533"
提示：
1 <= num1.length, num2.length <= 10^4
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
* https://leetcode.cn/problems/add-strings/
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
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

    public static void main(String[] args) {
        System.out.println('5' - '0');
    }

}
