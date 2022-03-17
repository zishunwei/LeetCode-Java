package mobi.zishun.hashmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 720. 词典中最长的单词
给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
输出："apple"
解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
* */
public class LongestWordInDictionary {
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String s : set) {
            int n = s.length();
            int m = ans.length();
            if (n < m) continue;
            // compareTo方法比较字典序
            if (n == m && s.compareTo(ans) > 0) {
                continue;
            }
            boolean ok = true;
            for (int i = 1; i <= n; i++) {
                String sub = s.substring(0, i);
                if (!set.contains(sub)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = s;
            }
        }
        return ans;
    }

    // 初版 - 代码较冗余
    public String longestWordV2(String[] words) {
        Set<String> hashSet = new HashSet<>(Arrays.asList(words));
        String result = "";
        // 初筛一遍， 找出第一个满足条件的word
        for (String word : hashSet) {
            if (check(word, hashSet)) {
                result = word;
                break;
            }
        }
        // 没有满足条件的word
        if (result.equals("")) {
            return result;
        }
        // 找出满足条件、最大/字典序最小的word
        for (String word : hashSet) {
            int m = word.length();
            int n = result.length();
            if ((m > n || (m == n && compare(word, result, m))) && check(word, hashSet)) {
                result = word;
            }
        }
        return result;
    }

    private boolean compare(String word, String result, int length) {
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) < result.charAt(i)) {
                return true;
            } else if (word.charAt(i) > result.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    private boolean check(String word, Set<String> hashSet) {
        for (int i = word.length() - 1; i > 0; i--) {
            if (!hashSet.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestWordInDictionary m = new LongestWordInDictionary();
        String[] words = {"m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat"};
        System.out.println(m.longestWord(words));
    }
}

