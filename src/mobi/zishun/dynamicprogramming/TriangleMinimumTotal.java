package mobi.zishun.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/*
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为11（即，2+3+5+1= 11）。
链接：https://leetcode-cn.com/problems/triangle
 */
public class TriangleMinimumTotal {
    // 动态规划
    public int minimumTotal(List<List<Integer>> triangle) {
        return 0;
    }

    // 递归 + 缓存方法
    public int minimumTotalV2(List<List<Integer>> triangle) {
        int n = triangle.size();
        cache = new Integer[n][triangle.get(n - 1).size()];
        return dfs(triangle, n, 0, 0);
    }

    private Integer[][] cache;

    private int dfs(List<List<Integer>> triangle, int n, int i, int j) {
        if (i == n) {
            return 0;
        }
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        int res = triangle.get(i).get(j) + Math.min(dfs(triangle, n, i + 1, j), dfs(triangle, n, i + 1, j + 1));
        cache[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        TriangleMinimumTotal m = new TriangleMinimumTotal();
        int[][] triangleArr = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> triangle = new ArrayList<>(triangleArr.length);
        for (int[] row : triangleArr) {
            List<Integer> rowList = new ArrayList<>(row.length);
            for (int num : row) {
                rowList.add(num);
            }
            triangle.add(rowList);
        }
        System.out.println(m.minimumTotal(triangle));
    }


}
