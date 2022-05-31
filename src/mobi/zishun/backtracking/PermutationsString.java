package mobi.zishun.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * 剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列。
你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

示例:
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
限制：
1 <= s 的长度 <= 8
* https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class PermutationsString {

    // 优化版 - 排序 + 记忆化回溯 + 剪枝掉重复的情况
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        visited = new boolean[chars.length];
        // 排序为了后续除去重复
        Arrays.sort(chars);
        dfs(chars, chars.length, new StringBuilder());
        return result.toArray(new String[chars.length]);
    }

    List<String> result = new LinkedList<>();

    boolean[] visited;

    private void dfs(char[] chars, int n, StringBuilder temp) {
        if (temp.length() == n) {
            result.add(temp.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            // 剪枝操作, 除去重复
            if (visited[i] || i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            temp.append(chars[i]);
            dfs(chars, n, temp);
            temp.deleteCharAt(temp.length() - 1);
            visited[i] = false;
        }
    }


    // 初版 - 全排列 + Set去重 - 时间复杂度稍高
    public String[] permutationV2(String s) {
        char[] chars = s.toCharArray();
        dfsV2(chars, 0, s.length());
        return result.toArray(new String[chars.length]);
    }

    Set<String> resulSet = new HashSet<>();

    private void dfsV2(char[] chars, int start, int n) {
        if (start == n) {
            resulSet.add(String.valueOf(chars));
            return;
        }
        for (int i = start; i < n; i++) {
            swap(chars, i, start);
            dfsV2(chars, start + 1, n);
            swap(chars, i, start);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        PermutationsString m = new PermutationsString();
        System.out.println(Arrays.toString(m.permutationV2("aab")));
    }

}
