package mobi.zishun.greedy;

import java.util.ArrayList;
import java.util.List;

/*
 * 2038. 如果相邻两个颜色均相同则删除当前颜色
总共有 n 个颜色片段排成一列，每个颜色片段要么是 'A' 要么是 'B' 。给你一个长度为 n 的字符串 colors ，其中 colors[i] 表示第 i 个颜色片段的颜色。
Alice 和 Bob 在玩一个游戏，他们 轮流 从这个字符串中删除颜色。Alice 先手 。
* 如果一个颜色片段为 'A' 且 相邻两个颜色 都是颜色 'A' ，那么 Alice 可以删除该颜色片段。Alice 不可以 删除任何颜色 'B' 片段。
如果一个颜色片段为 'B' 且 相邻两个颜色 都是颜色 'B' ，那么 Bob 可以删除该颜色片段。Bob 不可以 删除任何颜色 'A' 片段。
Alice 和 Bob 不能 从字符串两端删除颜色片段。
如果其中一人无法继续操作，则该玩家 输 掉游戏且另一玩家 获胜 。
* 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回 true，否则 Bob 获胜，返回 false。
示例 1：
输入：colors = "AAABABB"
输出：true
解释：
AAABABB -> AABABB
Alice 先操作。
她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
现在轮到 Bob 操作。
Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
因此，Alice 获胜，返回 true 。
* https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 */
public class RemoveColoredPieces {
    // 贪心
    // 分别计算出 Alice 和 Bob 的操作数。当 Alice 的操作数大于 Bob 的操作数时，Alice 获胜；否则，Bob 获胜。
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        char[] chars = colors.toCharArray();
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < n - 2; i++) {
            if (chars[i] == 'A' && chars[i + 1] == 'A' && chars[i + 2] == 'A') {
                countA++;
            }
            if (chars[i] == 'B' && chars[i + 1] == 'B' && chars[i + 2] == 'B') {
                countB++;
            }
        }
        return countA > countB;
    }

    // 初版 - 暴力模拟 - 超出时间限制
    public boolean winnerOfGameV2(String colors) {
        int n = colors.length();
        if (n < 3) {
            return false;
        }
        List<Character> colorsList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            colorsList.add(colors.charAt(i));
        }
        boolean res = false;
        while (true) {
            n = colorsList.size();

            int i = 0;
            for (; i < n - 2; i++) {
                if (colorsList.get(i) == 'A' && colorsList.get(i + 1) == 'A' && colorsList.get(i + 2) == 'A') {
                    colorsList.remove(i + 1);
                    res = true;
                    break;
                }
            }
            if (i == n - 2) {
                break;
            }

            n = colorsList.size();

            int j = 0;
            for (; j < n - 2; j++) {
                if (colorsList.get(j) == 'B' && colorsList.get(j + 1) == 'B' && colorsList.get(j + 2) == 'B') {
                    colorsList.remove(j + 1);
                    res = false;
                    break;
                }
            }
            if (j == n - 2) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RemoveColoredPieces m = new RemoveColoredPieces();
        System.out.println(m.winnerOfGame(""));
    }

}
