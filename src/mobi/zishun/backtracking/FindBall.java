package mobi.zishun.backtracking;

import java.util.Arrays;

/*
 * 1706. 球会落何处
用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
 */
public class FindBall {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return new int[]{-1};
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            dfs(grid, m, n, i, i, 0, res);
        }
        return res;
    }

    // i为出发位置的列索引（不变），column为当前列索引（可变）
    private void dfs(int[][] grid, int m, int n, int i, int column, int depth, int[] res) {
        if (depth == m) {
            res[i] = column;
            return;
        }
        if (column < n - 1 && grid[depth][column] == 1 && grid[depth][column + 1] == 1) {
            dfs(grid, m, n, i, ++column, ++depth, res);
        } else if (column > 0 && grid[depth][column] == -1 && grid[depth][column - 1] == -1) {
            dfs(grid, m, n, i, --column, ++depth, res);
        }
    }

    public int[] findBallV2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return new int[]{-1};
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            int depth = 0;
            int column = i;
            while (depth < m) {
                if (column < n - 1 && grid[depth][column] == 1 && grid[depth][column + 1] == 1) {
                    column++;
                    depth++;
                } else if (column > 0 && grid[depth][column] == -1 && grid[depth][column - 1] == -1) {
                    column--;
                    depth++;
                } else {
                    break;
                }
            }
            if (depth == m) {
                res[i] = column;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindBall m = new FindBall();
        int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};
        System.out.println(Arrays.toString(m.findBallV2(grid)));
    }
}
