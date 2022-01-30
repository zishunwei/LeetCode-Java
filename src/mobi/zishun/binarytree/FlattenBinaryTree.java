package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class FlattenBinaryTree {
    // 原地算法
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode curNode = root;
        while (curNode != null) {
            TreeNode next = curNode.left;
            // 无左子树-即直接遍历右子树
            if (next != null) {
                TreeNode preNode = next;
                while (preNode.right != null) {
                    preNode = preNode.right;
                }
                // 将右子数移动到preNode右下
                preNode.right = curNode.right;
                // 移动curr左子树为右子树
                curNode.left = null;
                curNode.right = next;
            }
            curNode = curNode.right;
        }
    }

    // 需额外空间（穿针引线）
    public void flattenV2(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode prev = null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.removeFirst();
            // 第一次循环（根节点为当前节点）
            if (prev != null) {
                prev.left = null;
                prev.right = curNode;
            }
            // 在遍历左子树之前就获得左右子节点的信息，并存入栈内，子节点的信息就不会丢失
            if (curNode.right != null) {
                stack.addFirst(curNode.right);
            }
            if (curNode.left != null) {
                stack.addFirst(curNode.left);
            }
            prev = curNode;
        }
    }

    // 需额外空间(逻辑简单)
    public void flattenV3(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode preHead = new TreeNode(-1);
        TreeNode prev = preHead;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.removeFirst();
            prev.right = curNode;
            prev.left = null;
            prev = prev.right;
            if (curNode.right != null) {
                stack.addFirst(curNode.right);
            }
            if (curNode.left != null) {
                stack.addFirst(curNode.left);
            }
        }
        root = preHead.right;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, null, new TreeNode(6));
        TreeNode root = new TreeNode(1, left, right);

        FlattenBinaryTree method = new FlattenBinaryTree();
        method.flattenV2(root);

        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }

}
