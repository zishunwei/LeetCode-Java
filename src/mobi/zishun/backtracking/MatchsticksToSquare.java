package mobi.zishun.backtracking;

import java.util.Arrays;

/*
 * 473. 火柴拼正方形
你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
* 你要用 所有的火柴棍 拼成一个正方形。
* 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
如果你能使这个正方形，则返回 true ，否则返回 false 。

示例 1:
输入: matchsticks = [1,1,2,2,2]
输出: true
解释: 能拼成一个边长为2的正方形，每边两根火柴。
示例 2:
输入: matchsticks = [3,3,3,3,4]
输出: false
解释: 不能用所有火柴拼成一个正方形。
提示:
1 <= matchsticks.length <= 15
1 <= matchsticks[i] <= 10^8
* https://leetcode.cn/problems/matchsticks-to-square/
 */
public class MatchsticksToSquare {
    // 排序 + 回溯
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int m : matchsticks) {
            sum += m;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int n = matchsticks.length;
        Arrays.sort(matchsticks);
        int[] edges = new int[4];

        // 从大到小回溯，先使用更长的火柴，可以降低时间复杂度
        dfs(matchsticks, sum / 4, edges, n - 1);
        return result;
    }

    boolean result;

    private void dfs(int[] matchsticks, int edgeLength, int[] edges, int index) {
        if (result) {
            return;
        }
        if (index == -1) {
            for (int i = 0; i < 4; i++) {
                if (edges[i] != edgeLength) {
                    return;
                }
            }
            result = true;
            return;
        }
        int cur = matchsticks[index];
        for (int i = 0; i < 4; i++) {
            if (edges[i] + cur <= edgeLength) {
                edges[i] += cur;
                dfs(matchsticks, edgeLength, edges, index - 1);
                edges[i] -= cur;
            }
        }
    }

}
