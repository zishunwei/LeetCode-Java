package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：
输入：n = 1
输出：["()"]
提示：
1 <= n <= 8
* https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs("", n, n, result);
        return result;
    }

    /**
     * @param left  左括号还有几个可以使用
     * @param right 右括号还有几个可以使用
     */
    private void dfs(String temp, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(temp);
            return;
        }
        // 剪枝（左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(temp + '(', left - 1, right, result);
        }
        if (right > 0) {
            dfs(temp + ')', left, right - 1, result);
        }
    }

}
