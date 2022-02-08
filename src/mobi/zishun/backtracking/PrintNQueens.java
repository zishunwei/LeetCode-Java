package mobi.zishun.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrintNQueens {

    private List<int[]> answer; // 所有解决方案

    private int n; // 所有解决方案

    public List<int[]> solveNQueens(int n) {
        answer = new LinkedList<>();
        this.n = n;
        result = new int[n];
        calNqueens(0);
        return answer;
    }

    private int[] result;//全局或成员变量,下标表示行,值表示queen存储在哪一列

    private void calNqueens(int row) { // 调用方式：calnqueens(0);
        if (row == n) {
            int[] res = new int[n];
            System.arraycopy(result, 0, res, 0, n);
            answer.add(res);
            printQueens(result);
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

    public void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < n; ++row) {
            for (int column = 0; column < n; ++column) {
                if (result[row] == column) {
                    System.out.print("Q  ");
                } else {
                    System.out.print("*  ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        PrintNQueens m = new PrintNQueens();
        List<int[]> ans = m.solveNQueens(4);
        System.out.println(m.n + "皇后问题共有" + ans.size() + "种解决方案");
        for (int i = 0; i < ans.size(); i++) {
            System.out.println("第" + (i + 1) + "种:" + Arrays.toString(ans.get(i)));
        }
    }
}
