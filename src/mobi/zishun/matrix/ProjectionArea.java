package mobi.zishun.matrix;

/*
 * 883. 三维形体投影面积
在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
返回 所有三个投影的总面积 。

示例 1：
输入：[[1,2],[3,4]]
输出：17
解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
示例 2:
输入：grid = [[2]]
输出：5
示例 3：
输入：[[1,0],[0,2]]
输出：8
提示：
n == grid.length == grid[i].length
1 <= n <= 50
0 <= grid[i][j] <= 50
* https://leetcode-cn.com/problems/projection-area-of-3d-shapes/
 */
public class ProjectionArea {
    // 数学 - O(n2) - 空间复杂度O(1)
    // xy 平面的投影面积等于网格上非零数值的数目；
    // yz 平面的投影面积等于网格上每一列最大数值之和；
    // zx 平面的投影面积等于网格上每一行最大数值之和。
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int res = 0;
        for (int x = 0; x < n; x++) {
            int yz = 0;
            int zx = 0;
            for (int y = 0; y < n; y++) {
                if (grid[x][y] > 0) {
                    res++; // 直接统计xy平面
                }
                yz = Math.max(yz, grid[x][y]); // 更新x列的最大数值
                zx = Math.max(zx, grid[y][x]); // 更新y行的最大数值，注意需要翻转x,y，保证迭代的都是y行的值
            }
            res += yz; // 更新yz平面
            res += zx; // 更新zx平面
        }
        return res;
    }

    // 初版 - 模拟构造三个平面 - O(k*n2) - 空间复杂度O(n2)
    public int projectionAreaV2(int[][] grid) {
        int n = grid.length;
        int res = 0;
        int[][] xzPlane = new int[51][51]; // xz平面
        int[][] yzPlane = new int[51][51]; // yz平面
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] > 0) {
                    res++; // 直接统计xy平面
                    for (int z = 1; z <= grid[x][y]; z++) {
                        xzPlane[x][z] = 1; // 更新xz平面
                        yzPlane[y][z] = 1; // 更新yz平面
                    }
                }
            }
        }
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                res += xzPlane[i][j];
                res += yzPlane[i][j];
            }
        }
        return res;
    }


}
