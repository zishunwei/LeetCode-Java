package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

/*
 * 530. 二叉搜索树的最小绝对差
给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
差值是一个正数，其数值等于两值之差的绝对值。
示例 1：
输入：root = [4,2,6,1,3]
输出：1
树中节点的数目范围是 [2, 10^4]
0 <= Node.val <= 105
* https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceBST {
    public int getMinimumDifference(TreeNode root) {
        traverse(root);
        return res;
    }

    int res = Integer.MAX_VALUE;
    int preVal = -1;

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        if (preVal != -1) {
            res = Math.min(res, preVal - root.val);
        }
        preVal = root.val;
        traverse(root.left);
    }
}
