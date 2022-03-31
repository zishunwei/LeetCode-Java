package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 236. 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
* 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
示例 2：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
示例 3：
输入：root = [1,2], p = 1, q = 2
输出：1
提示：
树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。
* https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

    // 根据以上定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
    // 1. p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
    // 2. p = root ，且 q 在 root 的左或右子树中；
    // 3. q = root ，且 p 在 root 的左或右子树中；
    // lowestCommonAncestor这个函数不要理解为找公共祖先，而就理解为帮两个节点找祖先
    // 传入的值是root, p, q，帮p和q找到一个祖先就行，找到两个就更好了，如果找不到就返回NULL
    // 在root->left里面找一次，root->right里面再找一次，
    // 如果某一边返回值是NULL， 那么说明两个值都在另一边 由于找的时候，一定是找的最近的祖先返回，
    // 所以这里直接返回前面的返回值就行了，可以保证是最近的公共祖先
    // 如果左右的返回值都不是NULL，那说明p和q分别在两边，则当前节点就是最近公共祖先 左右都找不到就直接返回NULL
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果当前结点 root 等于 NULL，则直接返回 NULL
        if (root == null) {
            return null;
        }
        // 如果 root 等于 p 或者 q ，那这棵树一定返回 p 或者 q
        if (root == p || root == q) {
            return root;
        }
        // 然后递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 left 和 right 表示
        // 所有 Node.val 互不相同
        // 当p、q都不在root的左/右子树中时，返回null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) { //如果 left 和 right 都非空，因为只给了 p 和 q 两个结点，都非空，
            // 说明一边一个，因此 root 是他们的最近公共祖先
            return root;
        } else if (left == null) { // 此时若left为空，那最终结果只要看 right；
            return right;
        } else if (right == null) { // 若 right 为空，那最终结果只要看 left
            return left;
        } else { // 如果 left 和 right 都为空，则返回空（其实已经包含在前面的情况中了）
            return null;
        }
    }

}
