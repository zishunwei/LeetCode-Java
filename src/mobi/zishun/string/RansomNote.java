package mobi.zishun.string;

/*
 * 383. 赎金信
给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
如果可以，返回 true ；否则返回 false 。
magazine 中的每个字符只能在 ransomNote 中使用一次。
示例 2：
输入：ransomNote = "aa", magazine = "ab"
输出：false
示例 3：
输入：ransomNote = "aa", magazine = "aab"
输出：true
提示：
1 <= ransomNote.length, magazine.length <= 105
ransomNote 和 magazine 由小写英文字母组成
* https://leetcode-cn.com/problems/ransom-note/
 */
public class RansomNote {
    // 字符统计
    public boolean canConstruct(String ransomNote, String magazine) {
        int m = ransomNote.length();
        int n = magazine.length();
        if (m > n) {
            return false;
        }
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < m; i++) {
            count1[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            count2[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] > count2[i]) {
                return false;
            }
        }
        return true;
    }

    // 初版 - 逐一删除 - 效率较低
    public boolean canConstructV2(String ransomNote, String magazine) {
        int m = ransomNote.length();
        int n = magazine.length();
        if (m > n) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            int index = magazine.indexOf(ransomNote.charAt(i));
            if (index == -1) {
                return false;
            } else {
                StringBuilder stringBuilder = new StringBuilder(magazine);
                magazine = stringBuilder.deleteCharAt(index).toString();
            }
        }
        return true;
    }

}
