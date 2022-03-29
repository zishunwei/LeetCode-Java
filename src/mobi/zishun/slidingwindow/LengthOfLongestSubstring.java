package mobi.zishun.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/*
 * 3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        while (right < n) {
            // 小优化
            if (left + res > n - 1) {
                break;
            }
            char cur = chars[right];
            if (!set.add(cur)) {
                while (set.contains(cur)) {
                    set.remove(chars[left++]);
                }
                set.add(cur);
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public int lengthOfLongestSubstringV2(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int res = 0;
        // 右指针
        int end = 0;
        // 哈希集合，记录每个字符是否出现过
        Set<Character> slidingWindow = new HashSet<>();
        // 遍历移动左指针
        for (int begin = 0; begin < n; begin++) {
            if (begin != 0) {
                // 左指针向右移动一格，移除前一个字符
                slidingWindow.remove(chars[begin - 1]);
            }
            while (end < n && !slidingWindow.contains(chars[end])) {
                // 不断地移动右指针
                slidingWindow.add(chars[end]);
                end++;
            }
            // 第 begin 到 end 个字符是一个极长的无重复字符子串
            res = Math.max(res, end - begin);
        }
        return res;
    }

}
