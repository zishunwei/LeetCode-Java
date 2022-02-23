package mobi.zishun.backtracking;

/*
 * 72. 编辑距离 - 莱文斯坦距离
 * 回溯算法 (+缓存) - 有DP版本
给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数 。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符
示例2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
链接：https://leetcode-cn.com/problems/edit-distance
 */
public class LevenshteinDistanceBacktracking {
    private int res = Integer.MAX_VALUE;

    private Integer[][] cache;

    public int minDistance(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        int m = a.length;
        int n = b.length;
        cache = new Integer[m][n];
        backtracking(a, b, m, n, 0, 0, 0);
        return res;
    }

    private void backtracking(char[] a, char[] b, int m, int n, int i, int j, int temp) {
        if (i == m || j == n) {
            if (i == m) {
                temp += n - j;
            } else {
                temp += m - i;
            }
            if (temp < res) {
                res = temp;
            }
            return;
        }
        // 缓存 - 剪枝
        if (cache[i][j] != null && temp >= cache[i][j] ) {
            return;
        } else {
            cache[i][j] = temp;
        }
        if (a[i] == b[j]) { //匹配
            backtracking(a, b, m, n, i + 1, j + 1, temp);
        } else { //不匹配
            // 在 a[i]前面添加一个跟 b[j]相同的字符 or 删除 a[i]，然后递归考察 a[i]和 b[j+1];
            backtracking(a, b, m, n, i, j + 1, temp + 1);
            // 在 b[j]前面添加一个跟 a[i]相同的字符 or 删除b[j] ，然后递归考察 a[i+1]和 b[j]；
            backtracking(a, b, m, n, i + 1, j, temp + 1);
            // 可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
            backtracking(a, b, m, n, i + 1, j + 1, temp + 1);
        }
    }

    public static void main(String[] args) {
        LevenshteinDistanceBacktracking m = new LevenshteinDistanceBacktracking();
        System.out.println(m.minDistance("mitcmu", "mtacnu")); // 4
    }


}
