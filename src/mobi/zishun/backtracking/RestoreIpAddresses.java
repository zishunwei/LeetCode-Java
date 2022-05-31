package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 93. 复原 IP 地址
有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
* 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

示例 1：
输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
示例 2：
输入：s = "0000"
输出：["0.0.0.0"]
示例 3：
输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
提示：
1 <= s.length <= 20
s 仅由数字组成
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if (n < 4 || n > 12) {
            return result;
        }
        dfs(s, n, 0, new ArrayList<>(4));
        return result;
    }

    List<String> result = new ArrayList<>();

    // start为当前处理的字符串的下标位置
    private void dfs(String s, int n, int start, List<String> temp) {
        if (start == n) {
            if (temp.size() == 4) {
                result.add(String.join(".", temp));
            }
            return;
        }
        // 看到剩下的不够了/或者超出了，就退出（剪枝）
        // n - start 表示剩余的还未分割的字符串的位数
        int curSize = temp.size();
        if ((n - start) < (4 - curSize) || (n - start) > 3 * (4 - curSize)) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (start + i >= n) {
                break;
            }
            // check 从start往后i位截取的字符串是否满足ip地址的一部分
            int end = start + i;
            int num = check(s, start, end);
            if (num != -1) {
                temp.add(String.valueOf(num));
                dfs(s, n, end + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }

    }

    private int check(String s, int start, int end) {
        int size = end - start + 1;
        // 大于 1 位的时候，不能以 0 开头
        if (size > 1 && s.charAt(start) == '0') {
            return -1;
        }
        // 转成数字
        int num = Integer.parseInt(s.substring(start, end + 1));
        return num <= 255 ? num : -1;
    }

    public static void main(String[] args) {
        RestoreIpAddresses m = new RestoreIpAddresses();
        System.out.println(m.restoreIpAddresses("101023"));
    }


}
