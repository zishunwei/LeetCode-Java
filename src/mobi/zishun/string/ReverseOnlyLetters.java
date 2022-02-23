package mobi.zishun.string;

/*
 * 917. 仅仅反转字母
给你一个字符串 s ，根据下述规则反转字符串：
所有非英文字母保留在原有位置。
所有英文字母（小写或大写）位置反转。
返回反转后的 s 。

示例 3：
输入：s = "Test1ng-Leet=code-Q!"
输出："Qedo1ct-eeLg=ntse-T!"
 */
public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (isLetter(chars[i]) && isLetter(chars[j])) {
                swap(chars, i, j);
                i++;
                j--;
            } else {
                if (!isLetter(chars[i])) {
                    i++;
                }
                if (!isLetter(chars[j])) {
                    j--;
                }
            }
        }
        return String.valueOf(chars);
    }

    private boolean isLetter(char c) {
//        return Character.isLetter(c);
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
