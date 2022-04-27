package mobi.zishun.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 417. 太平洋大西洋水流问题
有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
*
示例 1：
输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
示例 2：
输入: heights = [[2,1],[1,2]]
输出: [[0,0],[0,1],[1,0],[1,1]]
提示：
m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 10^5
* https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {
    // dfs
    // 如果直接以每个单元格作为起点模拟雨水的流动，则会重复遍历每个单元格，导致时间复杂度过高。
    // 为了降低时间复杂度，可以从矩阵的边界开始反向搜索寻找雨水流向边界的单元格，反向搜索时，每次只能移动到高度相同或更大的单元格。
    // 可以使用深度优先搜索实现反向搜索，搜索过程中需要记录每个单元格是否可以从太平洋反向到达以及是否可以从大西洋反向到达。
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, m, n, i, 0, pacific);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, m, n, 0, j, pacific);
        }

        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, m, n, i, n - 1, atlantic);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, m, n, m - 1, j, atlantic);
        }

        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int m, int n, int i, int j, boolean[][] ocean) {
        // 从外向内dfs，记录每个单元格是否可以从太平洋/大西洋反向到达
        if (ocean[i][j]) {
            return;
        }
        // 第一次到达这个位置，继续dfs其它位置
        ocean[i][j] = true;
        int curHeight = heights[i][j];
        // 将要走的新的位置比当前更高，说明可以从新位置流向当前位置
        // 当前位置可以流到海，说明新位置也可以
        if (i > 0 && heights[i - 1][j] >= curHeight) {
            dfs(heights, m, n, i - 1, j, ocean);
        }
        if (i < m - 1 && heights[i + 1][j] >= curHeight) {
            dfs(heights, m, n, i + 1, j, ocean);
        }
        if (j > 0 && heights[i][j - 1] >= curHeight) {
            dfs(heights, m, n, i, j - 1, ocean);
        }
        if (j < n - 1 && heights[i][j + 1] >= curHeight) {
            dfs(heights, m, n, i, j + 1, ocean);
        }
    }
}
