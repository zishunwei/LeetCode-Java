package mobi.zishun.string;

/*
 * 14. 最长公共前缀
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。

示例 1：
输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
* https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    // 纵向扫描
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        StringBuilder res = new StringBuilder();
        int i = 0;
        loop:
        while (i < strs[0].length()) {
            char cur = strs[0].charAt(i);
            for (int j = 1; j < n; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != cur) {
                    break loop;
                }
            }
            res.append(cur);
            i++;
        }
        return res.toString();
    }

}
