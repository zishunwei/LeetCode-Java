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

    private final List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 从 1 开始是题目的设定
        dfs(1, n, k);
        return result;
    }

    private void dfs(int start, int n, int k) {
        // 剪枝
        if (path.size() + (n - start + 1) < k) {
            return;
        }
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 遍历可能的搜索起点
        for (int i = start; i <= n; i++) {
            // 向路径变量里添加一个数
            path.add(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(i + 1, n, k);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.remove(path.size() - 1);
        }
    }


}
