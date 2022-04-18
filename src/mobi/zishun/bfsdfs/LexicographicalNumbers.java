package mobi.zishun.bfsdfs;

import java.util.LinkedList;
import java.util.List;

/*
 * 386. 字典序排数
给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。

示例 1：
输入：n = 13
输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
示例 2：
输入：n = 2
输出：[1,2]
提示：
1 <= n <= 5 * 10^4
* https://leetcode-cn.com/problems/lexicographical-numbers/
 */
public class LexicographicalNumbers {
    private final List<Integer> res = new LinkedList<>();

    // dfs字典树
    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= 9; i++) {
            // 提前剪枝
            if (i > n) {
                break;
            }
            dfs(n, i);
        }
        return res;
    }

    private void dfs(int n, int cur) {
        if (cur <= n) {
            res.add(cur);
        }
        cur *= 10;
        for (int i = 0; i <= 9; i++) {
            // 提前剪枝
            if (cur + i > n) {
                return;
            }
            dfs(n, cur + i);
        }
    }
}
