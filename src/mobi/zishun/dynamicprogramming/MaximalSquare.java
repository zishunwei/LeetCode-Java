package mobi.zishun.dynamicprogramming;

/*
 * 221. 最大正方形
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

示例 1：
输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
输出：4
示例 2：
输入：matrix = [['0','1'],['1','0']]
输出：1
示例 3：
输入：matrix = [['0']]
输出：0
提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'
* https://leetcode-cn.com/problems/maximal-square/
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // dp存的是边长（dp数是动态变化的，不是递增的）
        int[][] dp = new int[m][n];
        // 存储最大边长（dp过程中取最大值，不能取dp[m-1][n-1]）
        int maxSide = 0;
        // dp
        // 如果其本身是'1'，取其左、上、左上的dp最小值+1（如果最小值为1，说明当前可以构成一个边长为2的正方形）
        // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) { // 初始化 - 第一行/第一列 - matrix为'1'是对应位置dp即为1
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        // 输出结果 - 面积
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        MaximalSquare m = new MaximalSquare();
        char[][] matrix = {{'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}};

    }
}
