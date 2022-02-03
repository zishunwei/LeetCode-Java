package mobi.zishun.string;

import java.util.HashSet;
import java.util.Set;

/*
 * 1763. 最长的美好子字符串
链接：https://leetcode-cn.com/problems/longest-nice-substring
 */
public class LongestNiceSubstring {
    // 暴力解法
    public String longestNiceSubstring(String s) {
        int length = s.length();
        if (length <= 1) {
            return "";
        }
        String res = "";
        for (int i = 0; i <= length - 2; i++) {
            for (int j = i + 2; j <= length; j++) {
                String substr = s.substring(i, j);
                if (substr.length() > res.length() && checkNiceString(substr)) {
                    res = substr;
                }
            }
        }
        return res;
    }

    private boolean checkNiceString(String s) {
        Set<Character> set = new HashSet<>();
        char[] charArr = s.toCharArray();
        for (char c : charArr) {
            set.add(c);
        }
        for (char c : charArr) {
            char c1;
            if (Character.isUpperCase(c)) {
                c1 = Character.toLowerCase(c);
            } else {
                c1 = Character.toUpperCase(c);
            }
            if (!(set.contains(c1) && set.contains(c))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s = "abc";
//        System.out.println(s.substring(0, 2));
//        System.out.println(s.substring(1, 3));
        LongestNiceSubstring method = new LongestNiceSubstring();
        System.out.println(method.longestNiceSubstring("YabBbbbzaAay"));
    }

}
