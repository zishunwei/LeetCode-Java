package mobi.zishun.dynamicprogramming;

/*
 * 688. 骑士在棋盘上的概率
在一个n x n的国际象棋棋盘上，一个骑士从单元格 (row, column)开始，并尝试进行 k 次移动。
行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
骑士继续移动，直到它走了 k 步或离开了棋盘。
返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
* 输入: n = 3, k = 2, row = 0, column = 0
输出: 0.0625
解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
在每一个位置上，也有两种移动可以让骑士留在棋盘上。
骑士留在棋盘上的总概率是0.0625。
链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 */
public class KnightProbability {
    private final int[][] directions = {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    // 动态规划
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] states = new double[k + 1][n][n];
        // 初始化，k=0的情况概率都为1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                states[0][i][j] = 1;
            }
        }
        // 动态规划
        // 从步数为1（走了1步后）的状态向步数为k的状态迭代
        for (int step = 1; step <= k; step++) {
            // 同一步数状态遍历更新所有位置的概率
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 同一位置出发对八种方向进行模拟
                    for (int[] direction : directions) {
                        // 当前位置走一步之后的位置
                        int newi = i + direction[0];
                        int newj = j + direction[1];
                        // 当前位置走一步之后的位置在棋盘内
                        if (newi >= 0 && newi < n && newj >= 0 && newj < n) {
                            // i,j位置出发，此方向走一步还是在棋盘内
                            // i,j位置出发最终在棋盘的概率加上 newi,newj位置出发最终在棋盘上概率的1/8（少走一步）
                            states[step][i][j] += 0.125 * states[step - 1][newi][newj];
                        }
                    }
                }
            }
        }
        // 输出在row,column位置出发走k步最终在棋盘的概率
        return states[k][row][column];
    }

    // 递归 + 记忆化
    public double knightProbabilityV2(int n, int k, int row, int column) {
        cache = new double[n][n][k + 1];
        return dfs(n, k, row, column);
    }

    private double[][][] cache;

    public double dfs(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        if (cache[row][column][k] != 0) {
            return cache[row][column][k];
        }
        double sum = 0;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newColumn = column + direction[1];
            // 已离开了棋盘，跳过此次递归
            if (!(newRow >= 0 && newRow < n && newColumn >= 0 && newColumn < n)) {
                continue;
            }
            sum += 0.125 * dfs(n, k - 1, newRow, newColumn);
        }
        cache[row][column][k] = sum;
        return sum;
    }

    public static void main(String[] args) {
        KnightProbability m = new KnightProbability();
        System.out.println(m.knightProbability(3, 2, 0, 0));
    }
}
