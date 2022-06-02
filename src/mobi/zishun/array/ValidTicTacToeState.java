package mobi.zishun.array;

/*
 * 794. 有效的井字游戏
给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
*
以下是井字游戏的规则：
玩家轮流将字符放入空位（' '）中。
玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
当所有位置非空时，也算为游戏结束。
如果游戏结束，玩家不允许再放置字符。
示例 1：
输入：board = ["O  ","   ","   "]
输出：false
解释：玩家 1 总是放字符 "X" 。
示例 2：
输入：board = ["XOX"," X ","   "]
输出：false
解释：玩家应该轮流放字符。
示例 3:
输入：board = ["XOX","O O","XOX"]
输出：true
提示：
board.length == 3
board[i].length == 3
board[i][j] 为 'X'、'O' 或 ' '
* https://leetcode.cn/problems/valid-tic-tac-toe-state/
 */
public class ValidTicTacToeState {
    // 由于 X 先手，O 后手，两者轮流下子。因此 O 的数量不会超过 X，且两者数量差不会超过 1，否则为无效局面；
    // 若局面是 X 获胜，导致该局面的最后一个子必然是 X，此时必然有 X 数量大于 O（X 为先手），否则为无效局面；
    // 若局面是 O 获胜，导致该局面的最后一个子必然是 O，此时必然有 X 数量等于 O（X 为先手），否则为无效局面；
    // 局面中不可能出现两者同时赢（其中一方赢后，游戏结束）。
    public boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;
        char[][] boardChars = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String row = board[i];
            for (int j = 0; j < 3; j++) {
                char cur = row.charAt(j);
                boardChars[i][j] = cur;
                if (cur == 'X') {
                    countX++;
                } else if (cur == 'O') {
                    countO++;
                }
            }
        }
        // 因此 O 的数量不会超过 X，且两者数量差不会超过 1，否则为无效局面；
        if (countO > countX || countX - countO > 1) {
            return false;
        }
        // 统计X连成一线和O连成一线的次数
        int isXWin = 0;
        int isOWin = 0;
        for (int i = 0; i < 3; i++) {
            if (boardChars[0][i] == boardChars[1][i] && boardChars[1][i] == boardChars[2][i]) {
                if (boardChars[0][i] == 'X') {
                    isXWin++;
                } else if (boardChars[0][i] == 'O') {
                    isOWin++;
                }
            }
            if (boardChars[i][0] == boardChars[i][1] && boardChars[i][1] == boardChars[i][2]) {
                if (boardChars[i][0] == 'X') {
                    isXWin++;
                } else if (boardChars[i][0] == 'O') {
                    isOWin++;
                }
            }
        }
        if (boardChars[1][1] == 'X') {
            if (boardChars[0][0] == boardChars[1][1] && boardChars[1][1] == boardChars[2][2]) {
                isXWin++;
            }
            if (boardChars[0][2] == boardChars[1][1] && boardChars[1][1] == boardChars[2][0]) {
                isXWin++;
            }
        } else if (boardChars[1][1] == 'O') {
            if (boardChars[0][0] == boardChars[1][1] && boardChars[1][1] == boardChars[2][2]) {
                isOWin++;
            }
            if (boardChars[0][2] == boardChars[1][1] && boardChars[1][1] == boardChars[2][0]) {
                isOWin++;
            }
        }
        // 最终判断
        if (isXWin >= 1 && isOWin >= 1) { // X、O都赢了，不满足
            return false;
        } else if (isXWin == 1 && countX - 1 != countO) { // X赢了，X的个数不是比O多1，不满足
            return false;
        } else if (isOWin == 1 && countX != countO) { // O赢了，O的个数不和X一样，不满足
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidTicTacToeState m = new ValidTicTacToeState();
        String[] board = {"OXX", "XOX", "OXO"};
        System.out.println(m.validTicTacToe(board));
    }

}
