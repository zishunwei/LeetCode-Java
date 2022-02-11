package mobi.zishun.backtracking;

/*
 * 200. 岛屿数量
给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
链接：https://leetcode-cn.com/problems/number-of-islands
 */
public class NumIslands {
    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        int length1 = grid.length;
        int length2 = grid[0].length;
        visited = new boolean[length1][length2];
        int res = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    // 岛屿数量+1
                    res++;
                    // dfs此格相连的所有陆地, 并记录其已被访问
                    dfs(grid, i, j, length1, length2);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, int length1, int length2) {
        if (i < 0 || j < 0 || i >= length1 || j >= length2 || visited[i][j] || grid[i][j] == '0') {
            return;
        }
        // 记录此单元格已被访问
        visited[i][j] = true;
        dfs(grid, i - 1, j, length1, length2);
        dfs(grid, i + 1, j, length1, length2);
        dfs(grid, i, j - 1, length1, length2);
        dfs(grid, i, j + 1, length1, length2);
    }

}
