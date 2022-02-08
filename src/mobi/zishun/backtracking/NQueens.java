package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*
 * 51. N 皇后
n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
链接：https://leetcode-cn.com/problems/n-queens
 */

// 基于位运算的回溯的性能最佳，其次为基于数组，操纵字符串的性能最差
// 此为操纵数组
public class NQueens {

    private List<List<String>> answer; // 所有解决方案

    private int n; // 所有解决方案

    public List<List<String>> solveNQueens(int n) {
        answer = new LinkedList<>();
        this.n = n;
        result = new int[n];
        calNqueens(0);
        return answer;
    }

    private int[] result;//全局或成员变量,下标表示行,值表示queen存储在哪一列

    private void calNqueens(int row) { // 调用方式：calnqueens(0);
        if (row == n) {
            List<String> rows = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                char[] oneRow = new char[n];
                Arrays.fill(oneRow, '.');
                oneRow[result[i]] = 'Q';
                rows.add(String.valueOf(oneRow));
            }
            answer.add(rows);
            return; // n行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int column = 0; column < n; column++) { // 每一行都有n中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column;// 第row行的棋子放到了column列 (某一行，后面的值会对前面的值进行覆盖)
                calNqueens(row + 1); // 考察下一行 (关键逻辑，从第一行开始不断尝试到最后一行)
            }
        }
    }

    private boolean isOk(int row, int column) { //判断row行column列放置是否满足条件
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) { // 逐行往上考察每一行
            if (result[i] == column) { // 第i行的column列有棋子吗？
                return false;
            }
            if (leftUp >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
                if (result[i] == leftUp) {
                    return false;
                }
            }
            if (rightUp < n) {
                if (result[i] == rightUp) { // 考察右上对角线：第i行rightup列有棋子吗？
                    return false;
                }
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }


    public static void main(String[] args) {
        NQueens m = new NQueens();
        List<List<String>> ans = m.solveNQueens(4);
        System.out.println(m.n + "皇后问题共有" + ans.size() + "种解决方案");
        for (int i = 0; i < ans.size(); i++) {
            System.out.println("第" + (i + 1) + "种:" + ans.get(i));
        }
    }
}
