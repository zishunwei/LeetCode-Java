package mobi.zishun.others;

import mobi.zishun.model.TreeNode;

/*
235. 二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val < q.val) {
            return root;
        }
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
    }

    public void main(String[] args) {
        
    }
}
