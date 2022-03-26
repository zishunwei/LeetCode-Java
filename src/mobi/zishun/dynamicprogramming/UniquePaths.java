package mobi.zishun.dynamicprogramming;

/*
 * 62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？
示例 1：
输入：m = 3, n = 7
输出：28
提示：
1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109
 */
public class UniquePaths {
    // DP
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 暴力回溯 - 超过时间限制
    public int uniquePathsV2(int m, int n) {
        dfs(m, n, 0, 0);
        return res;
    }

    private int res = 0;

    private void dfs(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            res++;
            return;
        }
        if (i < m - 1) {
            dfs(m, n, i + 1, j);
        }
        if (j < n - 1) {
            dfs(m, n, i, j + 1);
        }
    }

    public static void main(String[] args) {
        UniquePaths m = new UniquePaths();
        System.out.println(m.uniquePaths(3, 7));
    }

}
