package mobi.zishun.matrix;

/*
 * 36. 有效的数独
请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
数字1-9在每一行只能出现一次。
数字1-9在每一列只能出现一次。
数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
注意：
一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
空白格用'.'表示。
链接：https://leetcode-cn.com/problems/valid-sudoku
 */
public class IsValidSudoku {
    // 一次遍历 - 以空间换时间
    public boolean isValidSudoku(char[][] board) {
        // 具体做法是，创建二维数组 rows 和 columns 分别记录数独的每一行和每一列中的每个数字的出现次数，
        // 创建三维数组 subboxes 记录数独的每一个小九宫格中的每个数字的出现次数
        // 如果任一个数组的值更新后的计数大于 1，则不符合有效的数独的条件，返回 false。
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int curIndex = board[i][j] - '0' - 1;
                if (curIndex != ('.' - '0' - 1)) {
                    rows[i][curIndex]++;
                    columns[curIndex][j]++;
                    subboxes[i / 3][j / 3][curIndex]++;
                    if (rows[i][curIndex] > 1 || columns[curIndex][j] > 1 || subboxes[i / 3][j / 3][curIndex] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 初版 - 按顺序遍历（check + 消除）- 代码复杂 - 复杂度高
    public boolean isValidSudokuV2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!res) {
                break;
            }

            for (int j = 0; j < 9; j++) {
                if (!res) {
                    break;
                }
                if (board[i][j] != '.') {
                    check(board, i, j, board[i][j]);
                    board[i][j] = '.';
                }
            }
        }
        return res;
    }

    private boolean res = true;

    private void check(char[][] board, int i, int j, int target) {
        for (int m = 0; m < 9; m++) {
            if (i != m) {
                if (board[m][j] == target) {
                    res = false;
                    return;
                }
            }
            if (j != m) {
                if (board[i][m] == target) {
                    res = false;
                    return;
                }
            }
        }
        for (int a = i / 3 * 3; a < i / 3 * 3 + 3; a++) {
            if (a == i) {
                continue;
            }
            for (int b = j / 3 * 3; b < j / 3 * 3 + 3; b++) {
                if (b == j) {
                    continue;
                }
                if (board[a][b] == target) {
                    res = false;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        IsValidSudoku m = new IsValidSudoku();
        char[][] board = {{'.', '.', '.', '.', '5', '.', '.', '1', '.'}, {'.', '4', '.', '3', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '3', '.', '.', '1'}, {'8', '.', '.', '.', '.', '.', '.', '2', '.'}, {'.', '.', '2', '.', '7', '.', '.', '.', '.'}, {'.', '1', '5', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '2', '.', '.', '.'}, {'.', '2', '.', '9', '.', '.', '.', '.', '.'}, {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
        System.out.println(m.isValidSudoku(board));
    }

}
