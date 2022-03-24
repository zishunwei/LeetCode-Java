package mobi.zishun.string;

/*
 * 387. 字符串中的第一个唯一字符
给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
示例 1：
输入: s = "leetcode"
输出: 0
示例 2:
输入: s = "loveleetcode"
输出: 2
示例 3:
输入: s = "aabb"
输出: -1
* s 只包含小写字母
* https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        for (char c : chars) {
            count[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
