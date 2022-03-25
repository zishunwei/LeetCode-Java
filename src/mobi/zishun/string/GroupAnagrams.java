package mobi.zishun.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 49. 字母异位词分组
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:
输入: strs = [""]
输出: [[""]]
示例 3:
输入: strs = ["a"]
输出: [["a"]]
提示：
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母
链接：https://leetcode-cn.com/problems/group-anagrams
 */
public class GroupAnagrams {
    // 排序 + 哈希表
    // O(nklogk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。
    public List<List<String>> groupAnagrams(String[] strs) {
        // key: 排序好的String，value：一个group结果
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> group = hashMap.getOrDefault(sortedStr, new ArrayList<String>());
            group.add(str);
            hashMap.put(sortedStr, group);
        }
        return new ArrayList<>(hashMap.values());
    }

        // 排序预处理 + 记忆化搜索 - 性能差 -代码冗余
    public List<List<String>> groupAnagramsV2(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        int n = strs.length;
        List<char[]> sortedStrs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char[] curStr = strs[i].toCharArray();
            Arrays.sort(curStr);
            sortedStrs.add(curStr);
        }
        boolean[] visted = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visted[i]) {
                List<String> result = new ArrayList<>();
                visted[i] = true;
                result.add(strs[i]);
                for (int j = i + 1; j < n; j++) {
                    if (!visted[j] && Arrays.equals(sortedStrs.get(i), sortedStrs.get(j))) {
                        result.add(strs[j]);
                        visted[j] = true;
                    }
                }
                results.add(result);
            }
        }
        return results;
    }

}
