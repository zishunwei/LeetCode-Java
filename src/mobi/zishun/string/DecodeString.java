package mobi.zishun.string;

/*
 * 394. 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例 1：
输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：
输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：
输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"
提示：
1 <= s.length <= 30
s 由小写英文字母、数字和方括号 '[]' 组成
s 保证是一个 有效 的输入。
s 中所有整数的取值范围为 [1, 300]
* https://leetcode-cn.com/problems/decode-string/
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        StringBuilder res = new StringBuilder();
        // num记录重复次数
        int num = 1;
        int i = 0;
        // count记录'['的数量
        int count = 0;
        int start = 0;
        while (i < n) {
            char cur = s.charAt(i);
            if (cur >= '0' && cur <= '9') {
                num = cur - '0';
                i++;
                while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
            } else if (cur == '[') {
                if (count == 0) {
                    start = i + 1;
                }
                count++;
                while (count > 0) {
                    i++;
                    if (s.charAt(i) == '[') {
                        count++;
                    } else if (s.charAt(i) == ']') {
                        count--;
                    }
                }
                // 递归生成子串
                String subRes = decodeString(s.substring(start, i));
                for (int j = 0; j < num; j++) {
                    res.append(subRes);
                }
                num = 1;
                i++;
            } else {
                res.append(cur);
                i++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        DecodeString m = new DecodeString();
        System.out.println(m.decodeString("10[a2[c]]"));
    }

}
