package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

/*
 * 538. 把二叉搜索树转换为累加树
给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
* 提醒一下，二叉搜索树满足下列约束条件：
* 节点的左子树仅包含键 小于 节点键的节点。
* 节点的右子树仅包含键 大于 节点键的节点。
* 左右子树也必须是二叉搜索树。
注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
* 示例 1：
输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
示例 4：
输入：root = [3,2,4,1]
输出：[7,9,4,10]
提示：
树中的节点数介于 0 和 104 之间。
每个节点的值介于 -104 和 104 之间。
树中的所有值 互不相同 。
给定的树为二叉搜索树。
* https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 */
public class BstToGreaterTree {
    // 右-中-左遍历BST，按顺序叠加当前节点值到sum，再将sum作为当前节点值
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private int sum = 0;

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}