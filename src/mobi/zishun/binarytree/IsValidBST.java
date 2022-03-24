package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
98. 验证二叉搜索树
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class IsValidBST {
    // BST需保证右子树中所有值都大于根节点值（左子树同）
    // 所以仅判断根节点的左右子树是否是BST是不足够的

    // 递归
    public boolean isValidBST(TreeNode root) {
        return isValidBSTRecursion(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTRecursion(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        // 递归缩短区间（左子节点值和其所有子节点值一定小于父节点）
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBSTRecursion(node.left, lower, node.val) && isValidBSTRecursion(node.right, node.val, upper);
    }

    // 中序遍历
    private long preVal = Long.MIN_VALUE;

    public boolean isValidBSTV2(TreeNode root) {
        if (root == null){
            return true;
        }

        // 访问左子树
        if (!isValidBST(root.left)){
            return false;
        }

        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= preVal){
            return false;
        }
        preVal = root.val;

        // 访问右子树
        if (!isValidBST(root.right)){
            return false;
        }

        // 遍历结束-没有通过上面三个if判断
        return true;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < 100; i++) {
            binarySearchTree.insert((int) (Math.random() * 1000));
        }

//        System.out.println(TraversalByRecursion.inorderTraversal(binarySearchTree.getTree()));
//        TreeNode left = new TreeNode(1, null, null);
//        TreeNode right = new TreeNode(4, new TreeNode(3), new TreeNode(6));
        TreeNode root = new TreeNode(1, new TreeNode(1, null, null), null);


        IsValidBST method = new IsValidBST();
        System.out.println(method.isValidBSTV2(root));
    }


}
