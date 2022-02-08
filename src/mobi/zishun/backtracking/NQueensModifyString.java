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
// 此为操纵字符串
 public class NQueensModifyString {
    private final List<List<String>> ans = new LinkedList<>();

    private int n;

    private List<String> res;

    private char[] oneRow;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        oneRow = new char[n];
        Arrays.fill(oneRow, '.');
        res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            res.add(String.valueOf(oneRow));
        }
        calNQueens(0);
        return ans;
    }

    private void calNQueens(int row) {
        if (row == n) {
            List<String> resCopy = new ArrayList<>(n);
            resCopy.addAll(res);
            ans.add(resCopy);
            return;
        }
        for (int column = 0; column < n; column++) {
            if (isOk(row, column)) {
                char[] curRow = oneRow.clone();
                curRow[column] = 'Q';
                res.set(row, String.valueOf(curRow));
                calNQueens(row + 1);
            }
        }
    }

    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (res.get(i).charAt(column) == 'Q') {
                return false;
            }
            if (leftUp >= 0) {
                if (res.get(i).charAt(leftUp) == 'Q') {
                    return false;
                }
            }
            if (rightUp < n) {
                if (res.get(i).charAt(rightUp) == 'Q') {
                    return false;
                }
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    public static void main(String[] args) {
        NQueensModifyString m = new NQueensModifyString();
        List<List<String>> result = m.solveNQueens(4);
        System.out.println(result);
    }

}