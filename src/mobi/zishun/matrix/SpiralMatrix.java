package mobi.zishun.matrix;

import java.util.ArrayList;
import java.util.List;

/*
 * 54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
* https://leetcode.cn/problems/spiral-matrix/
 */
public class SpiralMatrix {
    // 优化版 - 从外层向内层模拟 - 无需额外空间
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int x1 = m - 1;//最后一行
        int y1 = n - 1; //最后一列
        //起始位置
        int x2 = 0;
        int y2 = 0;
        //记录我们从数组里面拿出来元素的个数
        int count = 1;
        //数组中包含的所有元素的个数
        int total = m * n;
        while (count <= total) {//当数组里面元素没有全部读取到时继续循环
            //从左向右读取数组元素
            for (int j = y2; j <= y1 && count <= total; ++j) {
                result.add(matrix[x2][j]);
                count++;
            }
            //从上到下
            x2++;
            for (int i = x2; i <= x1 && count <= total; ++i) {
                result.add(matrix[i][y1]);
                count++;
            }
            //从右向左
            y1--;
            for (int j = y1; j >= y2 && count <= total; --j) {
                result.add(matrix[x1][j]);
                count++;
            }
            //从下向上
            x1--;
            for (int i = x1; i >= x2 && count <= total; --i) {
                result.add(matrix[i][y2]);
                count++;
            }
            y2++;
        }
        return result;
    }

    // 初版 - 设定好方向顺序，记录visted[][] & 记录上一个方向，代码冗余，需要额外空间
    public List<Integer> spiralOrderV2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int total = m * n;
        List<Integer> result = new ArrayList<>(total);
        int i = 0;
        int j = 0;
        int last = 0; //0: 向右；1：向下；2：向左；3：向右
        while (true) {
            result.add(matrix[i][j]);
            visited[i][j] = true;
            if (last == 0 && j < n - 1 && !visited[i][j + 1]) {
                j++;
            } else if (last == 1 && i < m - 1 && !visited[i + 1][j]) {
                i++;
            } else if (last == 2 && j > 0 && !visited[i][j - 1]) {
                j--;
            } else if (last == 3 && i > 0 && !visited[i - 1][j]) {
                i--;
            } else {
                if (j < n - 1 && !visited[i][j + 1]) {
                    last = 0;
                    j++;
                } else if (i < m - 1 && !visited[i + 1][j]) {
                    last = 1;
                    i++;
                } else if (j > 0 && !visited[i][j - 1]) {
                    last = 2;
                    j--;
                } else if (i > 0 && !visited[i - 1][j]) {
                    last = 3;
                    i--;
                } else {
                    break;
                }
            }
        }
        return result;
    }

}
