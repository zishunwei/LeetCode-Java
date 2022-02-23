package mobi.zishun.dynamicprogramming;

/*
 * 72. 编辑距离 - 莱文斯坦距离
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
public class LevenshteinDistance {
    public int minDistance(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        int n = a.length;
        int m = b.length;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        int[][] minDistances = new int[n][m];

        // 初始化第0行:a[0]与b[0]的编辑距离
        if (a[0] == b[0]) {
            minDistances[0][0] = 0;
        } else {
            minDistances[0][0] = 1;
        }
        // 初始化第0行:a[0]与b[0..j]的编辑距离
        for (int j = 1; j < m; j++) {
            if (a[0] == b[j]) { // 当前位相等，即只需填充差的位数（b[0..j]-1 == j）
                minDistances[0][j] = j;
            } else { // 当前位不等，从上一个状态+1
                minDistances[0][j] = minDistances[0][j - 1] + 1;
            }
        }
        // 初始化第0列:a[0..i]与b[0]的编辑距离
        for (int i = 1; i < n; i++) {
            if (a[i] == b[0]) {
                minDistances[i][0] = i;
            } else {
                minDistances[i][0] = minDistances[i - 1][0] + 1;
            }
        }

        // 动态规划
        // 如果：a[i]==b[j]，那么：min_edist(i, j)就等于：min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
        // 如果：a[i]!=b[j]，那么：min_edist(i, j)就等于：min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    // 当前能够匹配：i和j的匹配进度相同的情况minDistances[i - 1][j - 1]不需要+1，其它需要+1（删除/替换操作）
                    minDistances[i][j] = Math.min(Math.min(minDistances[i - 1][j] + 1,
                            minDistances[i][j - 1] + 1), minDistances[i - 1][j - 1]);
                } else {
                    // 当前不能匹配：所有状态转移都需要+1
                    minDistances[i][j] = Math.min(Math.min(minDistances[i - 1][j] + 1,
                            minDistances[i][j - 1] + 1), minDistances[i - 1][j - 1] + 1);
                }
            }
        }

        return minDistances[n - 1][m - 1];
    }

    public static void main(String[] args) {
        LevenshteinDistance m = new LevenshteinDistance();
        System.out.println(m.minDistance("mitcmu", "mtacnu")); // 4
    }


}
