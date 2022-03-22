package mobi.zishun.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 695. 岛屿的最大面积
给你一个大小为 m x n 的二进制矩阵 grid 。
岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
你可以假设 grid 的四个边缘都被 0（代表水）包围着。
岛屿的面积是岛上值为 1 的单元格的数目。
计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
https://leetcode-cn.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {

    // DFS
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, m, n, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int m, int n, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 +
                dfs(grid, m, n, i + 1, j) +
                dfs(grid, m, n, i, j + 1) +
                dfs(grid, m, n, i - 1, j) +
                dfs(grid, m, n, i, j - 1);
    }

    // BFS
    public int maxAreaOfIslandV2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 记录从当前节点开始bfs获得的岛屿面积
                int curArea = 0;
                Queue<int[]> queue = new LinkedList<int[]>();
                queue.offer(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] curPos = queue.poll();
                    // 当前位置点超过边界或者值为0 - 直接跳过
                    if (curPos[0] < 0 || curPos[0] >= m || curPos[1] < 0 || curPos[1] >= n || grid[curPos[0]][curPos[1]] != 1) {
                        continue;
                    }
                    // 当前计数+1，当前位置岛屿落海
                    curArea++;
                    grid[curPos[0]][curPos[1]] = 0;
                    // 往四个方向bfs遍历
                    for (int[] direction : directions) {
                        int newi = curPos[0] + direction[0];
                        int newj = curPos[1] + direction[1];
                        queue.offer(new int[]{newi, newj});
                    }
                }
                if (curArea > maxArea) {
                    maxArea = curArea;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        MaxAreaOfIsland m = new MaxAreaOfIsland();
        System.out.println(m.maxAreaOfIsland(grid));
    }

}
