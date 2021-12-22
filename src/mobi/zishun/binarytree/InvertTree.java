package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 226. 翻转二叉树
 *
     4
   /   \
  2     7
 / \   / \
1   3 6   9

     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class InvertTree {

    // 递归
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode res = new TreeNode(root.val);
        res.left = invertTree(root.right);
        res.right = invertTree(root.left);
        return res;
    }

}
