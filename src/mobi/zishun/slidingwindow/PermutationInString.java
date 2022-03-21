package mobi.zishun.slidingwindow;

import java.util.Arrays;

/*
 * 567. 字符串的排列
给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
换句话说，s1 的排列之一是 s2 的 子串 。
* s1 和 s2 仅包含小写字母
输入：s1 = "ab" s2 = "eidbaooo"
输出：true
解释：s2 包含 s1 的排列之一 ("ba").
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        // 存储字母的出现次数
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        // 初始化
        for (int i = 0; i < m; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(count1, count2)) {
            return true;
        }
        // 窗口滑动
        for (int i = m; i < n; i++) {
            count2[s2.charAt(i) - 'a']++;
            count2[s2.charAt(i - m) - 'a']--;
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }
        return false;
    }

    // 初版
    public boolean checkInclusionV2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // 存储字母的出现次数
        // 优化版本
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        // 初始化
        for (int i = 0; i < m; i++) {
            count1[chars1[i] - 'a']++;
            count2[chars2[i] - 'a']++;
        }
        // 可以直接调用api对比两个初始数组，这里使用自己的比较（只比较s1有的字母，更高效）
//        if (Arrays.equals(count1, count2)) {
        if (isEqual(chars1, m, count1, count2)) {
            return true;
        }
        // 窗口滑动
        for (int i = m; i < n; i++) {
            ++count2[chars2[i] - 'a'];
            --count2[chars2[i - m] - 'a'];
            if (isEqual(chars1, m, count1, count2)) {
                return true;
            }
        }
        // 初版 - 全量比较每个字母出现次数
//        for (int begin = 0; begin < n - m + 1; begin++) {
//            int[] count2 = new int[26];
//            for (int i = begin; i < begin + m; i++) {
//                count2[chars2[i] - 'a']++;
//            }
//            if (isEqual(chars1, m, count1, count2)) {
//                return true;
//            }
//        }
        return false;
    }

    // 只比较count1中字母出现次数
    private boolean isEqual(char[] chars1, int m, int[] count1, int[] count2) {
        for (int i = 0; i < m; i++) {
            if (count1[chars1[i] - 'a'] != count2[chars1[i] - 'a']) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInString m = new PermutationInString();
        String s1 = "ab";
        String s2 = "eidbaoo";
        System.out.println(m.checkInclusion(s1, s2));
    }


}
