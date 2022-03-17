package mobi.zishun.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 994. 腐烂的橘子
在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
输出：4
* 示例 3：
输入：grid = [[0,2]]
输出：0
解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
* https://leetcode-cn.com/problems/rotting-oranges/
 */
public class OrangesRotting {
    // bfs
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // 排除整个矩阵全是空格的情况
        boolean isEmpty = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    isEmpty = false;
                } else if (grid[i][j] == 1) {
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) {
            return 0;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int time = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int k = 0; k < curSize; k++) {
                int[] cur = queue.remove();
                int i = cur[0];
                int j = cur[1];
                for (int[] direction : directions) {
                    int newi = i + direction[0];
                    int newj = j + direction[1];
                    if (newi >= 0 && newi < m && newj >= 0 && newj < n && !visited[newi][newj] && grid[newi][newj] == 1) {
                        grid[newi][newj] = 2;
                        queue.offer(new int[]{newi, newj});
                        visited[newi][newj] = true;
                    }
                }
            }
            time++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return time - 1;
    }

}
