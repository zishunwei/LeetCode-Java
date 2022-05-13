package mobi.zishun.backtracking;

/*
 * 面试题 01.05. 一次编辑
字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
* 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。

示例 1:
输入:
first = "pale"
second = "ple"
输出: True
示例 2:
输入:
first = "pales"
second = "pal"
输出: False
* https://leetcode.cn/problems/one-away-lcci/
 */
public class OneEditAway {
    // 双指针 - 代码简洁
    // 本题要求两个字符串的编辑距离不超过1，我们不需要像编辑距离一样统计对比所有位置了，只需要依次比较不同，因为最多有一处。
    public boolean oneEditAway(String first, String second) {
        int m = first.length();
        int n = second.length();
        if (Math.abs(m - n) > 1) {
            return false;
        }
        boolean edited = false;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (first.charAt(i) == second.charAt(j)) { // 当前可以匹配上
                // 继续匹配下一个字符
                i++;
                j++;
            } else if (edited) { //当前匹配不上 + 已经编辑过
                return false;
            } else { // 当前匹配不上 + 未编辑过：有如下三种编辑方式（根据first、second的长度决定使用哪种）
                if (m > n) {
                    // 删除当前字符
                    i++;
                } else if (m < n) {
                    // 插入一个字符
                    j++;
                } else {
                    // 替换当前字符
                    i++;
                    j++;
                }
                edited = true;
            }
        }
        return true;
    }

    // 回溯写法 - 代码较多
    public boolean oneEditAwayV2(String first, String second) {
        m = first.length();
        n = second.length();
        string = first.toCharArray();
        target = second.toCharArray();
        dfs(0, 0, 0);
        return result;
    }

    private boolean result = false;
    private int m;
    private int n;
    private char[] string;
    private char[] target;

    private void dfs(int i, int j, int editCount) {
        if (result || editCount > 1) {
            return;
        }
        // 先判断 i 或者 j 是否已经到头
        if (i == m && j == n) {
            result = true;
        } else if (i == m) {
            // 插入一个字符
            dfs(i, j + 1, editCount + 1);
        } else if (j == n) {
            // 删除当前字符
            dfs(i + 1, j, editCount + 1);
        } else if (string[i] == target[j]) { // 当前可以匹配上
            // 继续匹配下一个字符
            dfs(i + 1, j + 1, editCount);
        } else { // 当前匹配不上，有如下三种处理方法（根据first、second的长度决定使用哪种）
            if (m > n) {
                // 删除当前字符
                dfs(i + 1, j, editCount + 1);
            } else if (m < n) {
                // 插入一个字符
                dfs(i, j + 1, editCount + 1);
            } else {
                // 替换当前字符
                dfs(i + 1, j + 1, editCount + 1);
            }
        }

    }

    public static void main(String[] args) {
        OneEditAway m = new OneEditAway();
        System.out.println(m.oneEditAway("", "a"));
    }


}
