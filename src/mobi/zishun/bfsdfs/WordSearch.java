package mobi.zishun.bfsdfs;

/*
 * 79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
示例 1：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 3：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
提示：
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成
进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
* https://leetcode-cn.com/problems/word-search/
 */
public class WordSearch {
    private boolean result = false;

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[] wordChars = word.toCharArray();
        int length = word.length();
        loop:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (result) {
                    break loop;
                }
                if (board[i][j] == wordChars[0]) {
                    // 同一个单元格内的字母不允许被重复使用
                    // 每次开始搜索时重置一个记录路径的数组
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    dfs(board, m, n, i, j, wordChars, length, 0, visited);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] board, int m, int n, int i, int j, char[] wordChars, int length, int index, boolean[][] visited) {
        if (result) {
            return;
        }
        if (index == length - 1) {
            result = true;
            return;
        }
        for (int[] direction : directions) {
            int newi = i + direction[0];
            int newj = j + direction[1];
            if (newi >= 0 && newi < m && newj >= 0 && newj < n &&
                    !visited[newi][newj] && board[newi][newj] == wordChars[index + 1]) {
                visited[newi][newj] = true;
                dfs(board, m, n, newi, newj, wordChars, length, index + 1, visited);
                visited[newi][newj] = false;
            }
        }
    }
}