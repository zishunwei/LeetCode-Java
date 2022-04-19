package mobi.zishun.string;

/*
 * 647. 回文子串
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
回文字符串 是正着读和倒过来读一样的字符串。
子字符串 是字符串中的由连续字符组成的一个序列。
具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

示例 1：
输入：s = "abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：
输入：s = "aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
提示：
1 <= s.length <= 1000
s 由小写英文字母组成
* https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class PalindromicSubstrings {

    // dp
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 状态：dp[i][j] 表示字符串s在[i,j]区间的子串是否是一个回文串。
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        // 从小到大dp [i,j]区间, 保证后面dp中判断的中间子串是之前判断过的
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                // 1.当前字符相等，2.两个字符中间没有间隔 or 中间字符串已被check为回文子串
                if (chars[i] == chars[j] && (j - i < 2 || dp[i + 1][j - 1])) {
                    res++;
                    dp[i][j] = true; // 更新此子串的状态
                }
            }
        }
        return res;
    }

    // dp优化 - 中心拓展
    public int countSubstringsV2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int res = n;
        for (int i = 0; i < 2 * n - 1; i++) {
            // left和right指针和中心点的关系是？
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            int left = i / 2;
            int right = i / 2 + i % 2;
            while (left >= 0 && right < n && chars[left] == chars[right]) {
                res++;
                left--;
                right++;
            }
        }
        return res;
    }


    // 暴力法
    public int countSubstringsV3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int res = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPalindromic(chars, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isPalindromic(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
