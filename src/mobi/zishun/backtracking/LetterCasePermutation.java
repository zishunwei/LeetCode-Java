package mobi.zishun.backtracking;

import java.util.LinkedList;
import java.util.List;

/*
 * 784. 字母大小写全排列
给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
输入：s = "a1b2"
输出：["a1b2", "a1B2", "A1b2", "A1B2"]
示例 2:
输入: s = "3z4"
输出: ["3z4","3Z4"]
* https://leetcode-cn.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<String> result = new LinkedList<>();
        dfs(chars, n, "", 0, result);
        return result;
    }

    private void dfs(char[] chars, int n, String temp, int index, List<String> result) {
        if (index == n) {
            result.add(temp);
            return;
        }
        char cur = chars[index];
        if (cur >= '0' && cur <= '9') {
            dfs(chars, n, temp + cur, index + 1, result);
        } else {
            dfs(chars, n, temp + cur, index + 1, result);
            if (Character.isLowerCase(cur)) {
                dfs(chars, n, temp + Character.toUpperCase(cur), index + 1, result);
            } else {
                dfs(chars, n, temp + Character.toLowerCase(cur), index + 1, result);
            }
        }
    }

}
