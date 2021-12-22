package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 100. 相同的树
  给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
