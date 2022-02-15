package mobi.zishun.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 1380. 矩阵中的幸运数
给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
幸运数是指矩阵中满足同时下列两个条件的元素：
在同一行的所有元素中最小
在同一列的所有元素中最大
* 示例
输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
输出：[15]
解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 */
public class LuckyNumbersInMatrix {
    // 初版 - 先找同一行最小值，再比较其列的其它元素
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 记录在同一行的所有元素中最小的元素的索引
        int[] recordMin = new int[matrix.length];
        for (int i = 0; i < m; i++) {
            recordMin[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < matrix[i][recordMin[i]]) {
                    recordMin[i] = j;
                }
            }
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int j = recordMin[i];
            int cur = matrix[i][j];
            int k;
            for (k = 0; k < m; k++) {
                if (matrix[k][j] > cur) {
                    break;
                }
            }
            if (k == m) {
                res.add(cur);
            }
        }
        return res;
    }

    // 预处理 - 预处理出每行的最小值数组minRow 和每列的最大值数组maxCol
    public List<Integer> luckyNumbersV2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 每行的最小值数组
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        // 每列的最大值数组
        int[] maxCol = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }

        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LuckyNumbersInMatrix m = new LuckyNumbersInMatrix();
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        System.out.println(m.luckyNumbers(matrix));
    }

}
