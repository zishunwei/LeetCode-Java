package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 701. 二叉搜索树中的插入操作
给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。
返回插入后二叉搜索树的根节点。 输入数据 保证 新值和原始二叉搜索树中的任意节点值都不同。
* 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
示例 1：
输入：root = [4,2,7,1,3], val = 5
输出：[4,2,7,1,3,5]
解释：另一个满足题目要求可以通过的树是：
示例 2：
输入：root = [40,20,60,10,30,50,70], val = 25
输出：[40,20,60,10,30,50,70,null,null,25]
* https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode insertNode = new TreeNode(val);
        if (root == null) {
            return insertNode;
        }
        TreeNode cur = root;
        while (true) {
            // 输入数据 保证 新值和原始二叉搜索树中的任意节点值都不同。
            if (val < cur.val) {
                if (cur.left == null) {
                    cur.left = insertNode;
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = insertNode;
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;
    }

}
