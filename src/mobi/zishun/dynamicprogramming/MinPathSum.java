package mobi.zishun.dynamicprogramming;

/*
 * 64. 最小路径和
 * 动态规划
给定一个包含非负整数的 m x n网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。
* 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] states = new int[m][n];
        // 初始化第一行和第一列的数据
        states[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            states[i][0] = states[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            states[0][j] = states[0][j - 1] + grid[0][j];
        }
        // 动态规划
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = grid[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }
        return states[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinPathSum m = new MinPathSum();
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(m.minPathSum(grid));
    }


}
