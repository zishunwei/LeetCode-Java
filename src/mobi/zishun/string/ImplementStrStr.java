package mobi.zishun.string;

/*
 * 28. 实现 strStr()
实现 strStr() 函数。
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
* 如果不存在，则返回  -1 。
说明：
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。

示例 1：
输入：haystack = "hello", needle = "ll"
输出：2
示例 2：
输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：
输入：haystack = "", needle = ""
输出：0
提示：
1 <= haystack.length, needle.length <= 10^4
haystack 和 needle 仅由小写英文字符组成
* https://leetcode.cn/problems/implement-strstr/
 */
public class ImplementStrStr {
    // indexOf() 源码实现
    public int strStr(String haystack, String needle) {
        int sourceCount = haystack.length();
        int targetCount = needle.length();
        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();

        if (0 >= sourceCount) {
            return -1;
        }
        if (targetCount == 0) {
            return 0;
        }

        char first = target[0];
        int max = sourceCount - targetCount;

        for (int i = 0; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }
            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = 1; j < end && source[j] == target[k]; j++, k++)
                    ;
                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }

}
