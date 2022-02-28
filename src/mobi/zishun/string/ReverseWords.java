package mobi.zishun.string;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 151. 翻转字符串里的单词
给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
说明：
* 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
* 翻转后单词间应当仅用一个空格分隔。
* 翻转后的字符串中不应包含额外的空格。
示例 1：
输入：s = "  the  sky is blue "
输出："blue is sky the"
链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 */
public class ReverseWords {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;
        for (; i < n; i++) {
            if (chars[i] != ' ') {
                break;
            }
        }
        Deque<StringBuilder> stack = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (; i < n; i++) {
            char c = chars[i];
            if (c != ' ') {
                stringBuilder.append(c);
            } else {
                if (stringBuilder.length() != 0) {
                    stack.addFirst(stringBuilder);
                }
                stringBuilder = new StringBuilder();
            }
        }
        if (stringBuilder.length() != 0) {
            stack.addFirst(stringBuilder);
        }
        // 出栈输出结果
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.removeFirst());
            res.append(' ');
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public static void main(String[] args) {
        ReverseWords m = new ReverseWords();
        System.out.println(m.reverseWords("  hello world  "));
    }
}
