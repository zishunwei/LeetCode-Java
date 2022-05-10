package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 124. 二叉树中的最大路径和
路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
* 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。

示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
示例 2：
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
提示：
树中节点数目范围是 [1, 3 * 10^4]
-1000 <= Node.val <= 1000
* https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class MaximumPathSum {
    public int maxPathSum(TreeNode root) {
        maxPathSumCrossCurFromSingleSide(root);
        return res;
    }

    // res记录递归过程中的最大路径和
    int res = Integer.MIN_VALUE;

    // 返回最大路径和(经过传入节点 + 只统计一个子树（这样可以和拼起来）)
    private int maxPathSumCrossCurFromSingleSide(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 核心地方：和 0 做一个取舍；如果是负值，则舍弃
        // 这样就可以不需要从叶子节点开始到叶子节点结束
        int leftMax = Math.max(0, maxPathSumCrossCurFromSingleSide(root.left));
        int rightMax = Math.max(0, maxPathSumCrossCurFromSingleSide(root.right));
        // 更新res，左边单边 + 右边单边 + 当前节点值
        res = Math.max(res, leftMax + rightMax + root.val);
        // 必须经过当前节点的最大路径和
        return Math.max(leftMax, rightMax) + root.val;
    }
}
