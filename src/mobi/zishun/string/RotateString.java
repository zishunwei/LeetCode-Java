package mobi.zishun.string;

/*
 * 796. 旋转字符串
给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。

示例 1:
输入: s = "abcde", goal = "cdeab"
输出: true
示例 2:
输入: s = "abcde", goal = "abced"
输出: false
提示:
1 <= s.length, goal.length <= 100
s 和 goal 由小写英文字母组成
* https://leetcode-cn.com/problems/rotate-string/
 */
public class RotateString {
    // 搜索子字符串
    // 如果 s 和 goal 的长度不一样，那么无论怎么旋转，s 都不能得到 goal，返回 false。
    // 字符串 s + s 包含了所有 s 可以通过旋转操作得到的字符串，只需要检查 goal 是否为 s+s 的子字符串即可。
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    public boolean rotateStringV2(String s, String goal) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.equals(goal)) {
                return true;
            }
            s = s.substring(1, n) + s.charAt(0);
        }
        return false;
    }

    public static void main(String[] args) {
        RotateString m = new RotateString();
        System.out.println(m.rotateString("abcde", "eabcd"));
    }

}
