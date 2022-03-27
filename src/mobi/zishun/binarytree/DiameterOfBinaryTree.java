package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 543. 二叉树的直径
给定一棵二叉树，你需要计算它的直径长度。
一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
示例 :
给定二叉树
          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
注意：两结点之间的路径长度是以它们之间边的数目表示。
* https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

    private int result = 0;

    // 递归优化版 - 在计算depth的同时计算出结果
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return result;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        result = Math.max(result, leftDepth + rightDepth); // 计算深度同时附加项：更新当前的结果
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 初版 - 递归 - 重复计算，时间复杂度高
    public int diameterOfBinaryTreeV2(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        int res = 0;
        res += depthV2(root.left);
        res += depthV2(root.right);
        if (root.left != null) {
            res = Math.max(res, diameterOfBinaryTree(root.left));
        }
        if (root.right != null) {
            res = Math.max(res, diameterOfBinaryTree(root.right));
        }
        return res;
    }

    private int depthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depthV2(root.left), depthV2(root.right));
    }

}
