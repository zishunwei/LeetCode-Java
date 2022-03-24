package mobi.zishun.matrix;

/*
 * 73. 矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
示例 1：
输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]
示例 2：
输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
* https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] isRowZero = new boolean[m];
        boolean[] isColumnZero = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    isRowZero[i] = true;
                    isColumnZero[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (isRowZero[i]) {
                for (int k = 0; k < n; k++) {
                    matrix[i][k] = 0;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            if (isColumnZero[j]) {
                for (int l = 0; l < m; l++) {
                    matrix[l][j] = 0;
                }
            }
        }
    }

}
