package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 110. 平衡二叉树
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class IsBalancedBinaryTree {
    // 自上而下 - 存在重复递归，复杂度高
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean res = Math.abs(getNodeHight(root.left) - getNodeHight(root.right)) <= 1;
        return res && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getNodeHight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getNodeHight(root.left), getNodeHight(root.right));
    }

    // 自下而上
    public boolean isBalancedV2(TreeNode root) {
        return true;
    }


}
