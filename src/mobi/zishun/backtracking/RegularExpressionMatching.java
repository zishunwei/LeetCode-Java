package mobi.zishun.backtracking;

/*
 * 10. 正则表达式匹配
给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        char[] string = s.toCharArray();
        char[] pattern = p.toCharArray();
        int m = s.length();
        int n = p.length();
        dfs(string, pattern, m, n, 0, 0);
        return matched;
    }

    private boolean matched = false;

    private void dfs(char[] string, char[] pattern, int m, int n, int i, int j) {
        if (matched) {
            return;
        }
        if (j == n) {
            if (i == m) { // s,p都到达末尾，匹配成功
                matched = true;
            }
            // p到达末尾，s好没到达末尾，匹配失败
            return;
        }
        // 当前字符可以匹配，注意保证i<m
        boolean curMatch = i < m && (string[i] == pattern[j] || pattern[j] == '.');
        if (j < n - 1 && pattern[j + 1] == '*') { // p的下一个为'*'
            // 考虑'*'匹配0个元素，直接跳过
            dfs(string, pattern, m, n, i, j + 2);
            // 如果当前是匹配的，则考虑'*'匹配1个及多个的情况
            if (curMatch) {
                // 保持j不变（还是在'*'前一个的位置），i往后一位
                dfs(string, pattern, m, n, i + 1, j);
            }
        } else if (curMatch) {
            // 只是当前匹配，都匹配下一位
            dfs(string, pattern, m, n, i + 1, j + 1);
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching m = new RegularExpressionMatching();
        System.out.println(m.isMatch("ab", ".*c"));
    }

}
