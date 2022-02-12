package mobi.zishun.backtracking;

/*
 * 1020. 飞地的数量
给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
链接：https://leetcode-cn.com/problems/number-of-enclaves
 */
public class NumEnclaves {

    public int numEnclaves(int[][] grid) {
        int length1 = grid.length;
        int length2 = grid[0].length;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if ((i == 0 || i == length1 - 1 || j == 0 || j == length2 - 1) && grid[i][j] == 1) {
                    dfs(grid, i, j, length1, length2);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, int length1, int length2) {
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        if (i > 0) {
            dfs(grid, i - 1, j, length1, length2);
        }
        if (i < length1 - 1) {
            dfs(grid, i + 1, j, length1, length2);
        }
        if (j > 0) {
            dfs(grid, i, j - 1, length1, length2);
        }
        if (j < length2 - 1) {
            dfs(grid, i, j + 1, length1, length2);
        }
    }

    public static void main(String[] args) {
        NumEnclaves m = new NumEnclaves();
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        System.out.println(m.numEnclaves(grid));
    }

}
