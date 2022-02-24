package mobi.zishun.dynamicprogramming;

/*
 * 1143. 最长公共子序列
给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
* 示例 1：
输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。
链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();
        int[][] dp = new int[m][n];
        // 初始化
        if (a[0] == b[0]) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (a[i] == b[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < n; j++) {
            if (a[0] == b[j]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        // 动态规划
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    // dp[i - 1][j], dp[i][j - 1]的结果里a[i] or b[j]已经在之前匹配过（加过1了），不能再+1
                    // 这里我琢磨了好久，理解它的核心其实在max这一个限制。因为是max，所以它的取值是所包含的各种可能性里面最大值。
                    // 如果我们取max_lcs(i-1,j)+1,那么在aba和a这种情况下我们会得到2而不是1。
                    // 而max这个限制就包含了aba这种情况的处理，所以我们不能加一。而只有i-1,j-1是一定确保加一的。
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence m = new LongestCommonSubsequence();
        System.out.println(m.longestCommonSubsequence("abcde", "ace")); //2
    }
}
