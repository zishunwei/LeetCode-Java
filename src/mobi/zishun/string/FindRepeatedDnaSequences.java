package mobi.zishun.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 187. 重复的DNA序列
DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
例如，"ACGAATTCCG" 是一个 DNA序列 。
在研究 DNA 时，识别 DNA 中的重复序列非常有用。
给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。

示例 1：
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
示例 2：
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
提示：
0 <= s.length <= 105
s[i]=='A'、'C'、'G' or 'T'
* https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class FindRepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new LinkedList<>();
        Map<String, Integer> count = new HashMap<String, Integer>();
        int n = s.length();
        for (int i = 0; i <= n - 10; i++) {
            String sub = s.substring(i, i + 10);
            count.put(sub, count.getOrDefault(sub, 0) + 1);
            if (count.get(sub) == 2) {
                result.add(sub);
            }
        }
        return result;
    }

}
