package mobi.zishun.dynamicprogramming;

/*
 * 5. 最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
 */
public class LongestPalindrome {
    // DP
    public String longestPalindrome(String s){
        return null;
    }

    // 暴力优化 - 从大到小check - 滑动窗口
    public String longestPalindromeV2(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // 单次检查起始索引
        int start = 0;
        // 记录要check的字符串长度
        int count = n - 1;
        // 从大到小check
        while (count > 0) {
            // 结束索引
            int end = start + count;
            // 滑动窗口check此count长度下的所有字符串情况
            while (end < n) {
                if (checkPalindrome(chars, start, end)) {
                    // 因为是从大到小遍历，第一次满足的情况即为最长的回文子串
                    return s.substring(start, end + 1);
                }
                start++;
                end++;
            }
            // 此count情况下所有长度已遍历完毕
            // 重置start为0
            // count--, 继续遍历
            start = 0;
            count--;
        }
        return s.substring(0, 1);
    }

    private boolean checkPalindrome(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
