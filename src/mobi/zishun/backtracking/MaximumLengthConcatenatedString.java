package mobi.zishun.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 1239. 串联字符串的最大长度
给定一个字符串数组 arr，字符串 s 是将 arr 的含有 不同字母 的 子序列 字符串 连接 所得的字符串。
请返回所有可行解 s 中最长长度。
子序列 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。

示例 1：
输入：arr = ["un","iq","ue"]
输出：4
解释：所有可能的串联组合是：
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
最大长度为 4。
示例 2：
输入：arr = ["cha","r","act","ers"]
输出：6
解释：可能的解答有 "chaers" 和 "acters"。
提示：
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] 中只含有小写英文字母
* https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaximumLengthConcatenatedString {

    // 回溯 + 后缀和 - 剪枝
    public int maxLength(List<String> arr) {
        int n = arr.size();
        // 保存从当前位置及其后面所有字符串元素的长度和
        int[] lengthSumArr = new int[n];
        lengthSumArr[n - 1] = arr.get(n - 1).length();
        for (int i = n - 2; i >= 0; i--) {
            lengthSumArr[i] = arr.get(i).length() + lengthSumArr[i + 1];
        }
        // dfs
        dfs(arr, lengthSumArr, n, 0, 0, new HashSet<Character>());
        return result;
    }

    int result = 0;

    private void dfs(List<String> arr, int[] lengthSumArr, int n, int index, int count, Set<Character> hashSet) {
        if (index == n) {
            result = Math.max(result, count);
            return;
        }
        // 剪枝 - 当前count + 后缀和 已经小于等于result了，说明无需继续递归当前路线
        if (lengthSumArr[index] + count <= result) {
            return;
        }
        String cur = arr.get(index);
        // 不使用当前的字符串
        dfs(arr, lengthSumArr, n, index + 1, count, hashSet);

        // 如果可以，使用当前的字符串
        // 判断是否可以使用当前字符串
        Set<Character> tempSet = new HashSet<Character>();
        for (int i = 0; i < cur.length(); i++) {
            // 注意：当前字符串有重复字符的情况，也不满足条件，增加tempSet的自重复判断
            if (hashSet.contains(cur.charAt(i)) || tempSet.contains(cur.charAt(i))) {
                tempSet = new HashSet<Character>();
                break;
            } else {
                tempSet.add(cur.charAt(i));
            }
        }
        // 使用当前字符串，注意hashSet需要回溯
        if (!tempSet.isEmpty()) {
            hashSet.addAll(tempSet);
            dfs(arr, lengthSumArr, n, index + 1, count + cur.length(), hashSet);
            hashSet.removeAll(tempSet);
        }
    }

    public static void main(String[] args) {
        MaximumLengthConcatenatedString m = new MaximumLengthConcatenatedString();
        List<String> arr = Arrays.asList("aa", "bb");
        System.out.println(m.maxLength(arr));
    }


}
