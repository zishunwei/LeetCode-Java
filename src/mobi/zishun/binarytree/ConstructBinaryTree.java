package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * 105. 从前序与中序遍历序列构造二叉树
给定两个整数数组 preorder 和 inorder ，
其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
* 示例 1:
输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]
* 示例 2:
输入: preorder = [-1], inorder = [-1]
输出: [-1]
* 提示:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder 和 inorder 均 无重复 元素
inorder 均出现在 preorder
preorder 保证 为二叉树的前序遍历序列
inorder 保证 为二叉树的中序遍历序列
* https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTree {

    // 存储中序遍历的节点位置
    private final Map<Integer, Integer> hashMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        return recursion(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    // inorder 和 inEnd可去掉
    private TreeNode recursion(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // preorder 为空，直接返回 null
        if (preStart == preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        // 在中序遍历中找到根节点的位置
        int inRootIndex = hashMap.get(rootVal);
        // 中序遍历中找到根节点的位置左边即为左子树的节点，右边即为右子数的节点
        int leftNum = inRootIndex - inStart; // 左子树节点的树目
        // 递归的构造左子树
        root.left = recursion(preorder, preStart + 1, preStart + 1 + leftNum, inorder, inStart, inRootIndex);
        // 递归的构造右子树
        root.right = recursion(preorder, preStart + 1 + leftNum, preEnd, inorder, inRootIndex + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTree m = new ConstructBinaryTree();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode result = m.buildTree(preorder, inorder);
        System.out.println(result);
    }

}
