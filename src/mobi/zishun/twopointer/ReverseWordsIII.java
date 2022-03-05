package mobi.zishun.twopointer;

/*
 * 557. 反转字符串中的单词 III
给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
输入：s = "Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"
 */
public class ReverseWordsIII {
    public String reverseWords(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        for (int j = 0; j < n; j++) {
            if (chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
        }
        reverse(chars, i, n - 1);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

}
