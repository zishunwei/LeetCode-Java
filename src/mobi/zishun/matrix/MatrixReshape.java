package mobi.zishun.matrix;

/*
 * 566. 重塑矩阵
在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
示例 1：
输入：mat = [[1,2],[3,4]], r = 1, c = 4
输出：[[1,2,3,4]]
https://leetcode-cn.com/problems/reshape-the-matrix/
 */
public class MatrixReshape {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int length1 = mat.length;
        int length2 = mat[0].length;
        if (length1 * length2 != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        int m = 0;
        int n = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                res[m][n] = mat[i][j];
                if (n == c - 1) {
                    m++;
                    n = 0;
                } else {
                    n++;
                }
            }
        }
        return res;
    }

}
