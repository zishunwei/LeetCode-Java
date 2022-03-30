package mobi.zishun.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/*
 * 139. 单词拆分
 * 完全背包问题
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。
示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅有小写英文字母组成
wordDict 中的所有字符串 互不相同
* https://leetcode-cn.com/problems/word-break/
 */
public class WordBreak {

    // dp
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // dp[i]代表s[0..i-1]是否满足条件
                // dp[j]代表s[0..j-1]是否满足条件
                // wordDict.contains(s.substring(j, i))检查剩余部分s[j..i-1]是否满足条件
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // dp优化
    // 时间复杂度: O(N * M), N是字符串s的长度, M是数组wordDict的长度
    // 我们对代码中的二重循环稍作优化, 时间效率会提高。
    // 主要区别是: 内层循环改为遍历wordDict数组, 而不是继续遍历字符串s了, 另外使用了一种剪枝技巧。
    public boolean wordBreakV2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            // 剪枝优化
            // dp[i - 1]是false时, 是无法完成拆分的, 指针i跳到下一个index
            if (!dp[i - 1]) {
                continue;
            }
            // 能执行到这, 说明子串s[0:i-1]都是能拆分的。
            // 遍历数组wordDict, 尝试从s中取出新的子串(这个子串的长度和当前循环的word相等, 即s[i-1:j]),
            // 如果这个子串恰好与当前word相同, 那么子串s[0:j]也都是能拆分的。重复以上过程
            for (String word : wordDict) {
                // 判断i位置加上当前word的j位置是否满足条件
                int j = i - 1 + word.length();
                // 指针j也不能越界, 即j <= len
                // s[i-1:j]等于word，说明s[0:j]满足条件
                if (j <= n && s.substring(i - 1, j).equals(word)) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        WordBreak m = new WordBreak();
        System.out.println(m.wordBreak("ddadddbdddadd", Arrays.asList("dd", "ad", "da", "b")));
    }

}
