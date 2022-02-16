package mobi.zishun.backtracking;

/*
 * 64. 最小路径和
 * 回溯 + 记忆化 （动态规划+从后往前递归版本见dp包）
给定一个包含非负整数的 m x n网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。
* 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
public class MinPathSumBacktracking {
//    private int minDistance = Integer.MAX_VALUE;

    private Integer[][] cache;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        cache = new Integer[m][n];
        dfs(grid, 0, 0, m, n, 0);
        return cache[m - 1][n - 1];
//        return minDistance;
    }

    private void dfs(int[][] grid, int i, int j, int m, int n, int temp) {
        temp += grid[i][j];
        if (cache[i][j] != null && temp >= cache[i][j]) {
            return;
        } else {
            cache[i][j] = temp;
        }
        if (i == m - 1 && j == n - 1) {
//            if (temp < minDistance) {
//                minDistance = temp;
//            }
            return;
        }
        if (i < m - 1) {
            dfs(grid, i + 1, j, m, n, temp);
        }
        if (j < n - 1) {
            dfs(grid, i, j + 1, m, n, temp);
        }
    }

    public static void main(String[] args) {
        MinPathSumBacktracking m = new MinPathSumBacktracking();
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(m.minPathSum(grid));
    }

}
