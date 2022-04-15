package mobi.zishun.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

示例 1:
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
示例 2:
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
提示:
1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母
* https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagrams {
    // 滑动窗口+计数数组 - 性能优于哈希表
    public List<Integer> findAnagrams(String s, String p) {
        int n = p.length();
        // 模式串字符计数
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[p.charAt(i) - 'a']++;
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = n - 1;
        List<Integer> res = new LinkedList<>();
        // 需要判断的字符串子串字符计数
        int[] curFreq = new int[26];
        loop1:
        while (right < chars.length) {
            for (; left <= right; left++) {
                if (freq[chars[left] - 'a'] > 0) {
                    curFreq[chars[left] - 'a']++;
                } else { // 优化点 - 遇到模式串里没有的字符，left直接跳到下一个并重置curFreq
                    left++;
                    right = left + n - 1;
                    curFreq = new int[26];
                    continue loop1;
                }
            }
            // 此时上述for循环跑完了：left == right + 1， right不变
            if (Arrays.equals(freq, curFreq)) {
                res.add(left - n);
            }
            curFreq[chars[left - n] - 'a']--;
            right++;
        }
        return res;
    }

    // 滑动窗口+hashmap
    public List<Integer> findAnagramsV2(String s, String p) {
        int n = p.length();
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char cur = p.charAt(i);
            freq.put(cur, freq.getOrDefault(cur, 0) + 1);
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = n - 1;
        List<Integer> res = new LinkedList<>();
        Map<Character, Integer> curFreq = new HashMap<>();
        loop1:
        while (right < chars.length) {
            for (; left <= right; left++) {
                if (freq.containsKey(chars[left])) {
                    curFreq.put(chars[left], curFreq.getOrDefault(chars[left], 0) + 1);
                } else { // 遇到模式串里没有的字符，直接跳过并重置
                    left++;
                    right = left + n - 1;
                    curFreq = new HashMap<>();
                    continue loop1;
                }
            }
            // 此时 left == right + 1
            if (freq.equals(curFreq)) {
                res.add(left - n);
            }
            if (curFreq.get(chars[left - n]) > 1) {
                curFreq.put(chars[left - n], curFreq.get(chars[left - n]) - 1);
            } else {
                curFreq.remove(chars[left - n]);
            }
            right++;
        }
        return res;
    }

}
