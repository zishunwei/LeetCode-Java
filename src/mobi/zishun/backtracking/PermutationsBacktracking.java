package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 46. 全排列
 * 回溯思想-基于树形结果dfs
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class PermutationsBacktracking {
    // 使用一个全局变量数组保存所有可能的全排列
    private final List<List<Integer>> res = new LinkedList<>();

    // 记录每个num调用时是否被选择过
    private boolean[] used;

    // 记录一种全排列情况
    private List<Integer> path;

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        used = new boolean[n];
        path = new ArrayList<>(n);
        permuteDFS(nums, n, 0);
        return res;
    }

    private void permuteDFS(int[] nums, int n, int depth) {
        if (depth == n) {
            List<Integer> pathCopy = new ArrayList<>(path);
            res.add(pathCopy);
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：
        // 在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                // 继续向下递归（向下遍历递归树）
                permuteDFS(nums, n, depth + 1);
                // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        PermutationsBacktracking m = new PermutationsBacktracking();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = m.permute(nums);
        System.out.println(res);
    }

}