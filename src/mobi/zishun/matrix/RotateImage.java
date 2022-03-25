package mobi.zishun.matrix;

import java.util.Arrays;

/*
 * 48. 旋转图像
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。
* 请不要 使用另一个矩阵来旋转图像。
示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]
* https://leetcode-cn.com/problems/rotate-image/
 */
public class RotateImage {
    // 翻转操作很容易
    // 顺时针旋转 90 度 = 水平翻转 + 主对角线翻转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            // 空间复杂度 - O(1)
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
            // 空间复杂度 - O(n)
//            int[] temp = matrix[0];
//            matrix[0] = matrix[n - i - 1];
//            matrix[n - i - 1] = temp;
        }
        // 主对角线翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage m = new RotateImage();
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        m.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
