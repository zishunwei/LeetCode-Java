package mobi.zishun.slidingwindow;

/*
 * 424. 替换后的最长重复字符
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
在执行上述操作后，返回包含相同字母的最长子字符串的长度。

示例 1：
输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。
示例 2：
输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
提示：
1 <= s.length <= 105
s 仅由大写英文字母组成
0 <= k <= s.length
* https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int res = 0;
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        // 记录每个字符出现的频率
        int[] counts = new int[26];
        // 记录字符出现的最大频率
        int maxCount = 0;
        while (right < n) {
            counts[chars[right] - 'A']++;
            maxCount = Math.max(maxCount, counts[chars[right] - 'A']);
            // 如果滑动窗口的长度大于字符能够达到的最大频率
            // 即窗口内不可能出现全是重复的字符，因此需要将窗口的左端向前滑动（缩小窗口）
            if (right - left + 1 > maxCount + k) {
                counts[chars[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

}
