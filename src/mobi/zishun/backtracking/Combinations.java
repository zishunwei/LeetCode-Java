package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 77. 组合
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
你可以按 任何顺序 返回答案。
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
提示：
1 <= n <= 20
1 <= k <= n
 */
public class Combinations {
    private final List<List<Integer>> result = new ArrayList<>();

    private final List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return result;
    }

    private void dfs(int i, int n, int k) {

    }


}
