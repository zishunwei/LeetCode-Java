package mobi.zishun.bst;

import mobi.zishun.binarytree.SerializeDeserializeBinaryTree;
import mobi.zishun.model.TreeNode;

/*
 * 450. 删除二叉搜索树中的节点
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
* 返回二叉搜索树（有可能被更新）的根节点的引用。
一般来说，删除节点可分为两个步骤：
首先找到需要删除的节点；
如果找到了，删除它。

示例 1:
输入：root = [5,3,6,2,4,null,7], key = 3
输出：[5,4,6,2,null,null,7]
解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
另一个正确答案是 [5,2,6,null,4,null,7]。
示例 2:
输入: root = [5,3,6,2,4,null,7], key = 0
输出: [5,3,6,2,4,null,7]
解释: 二叉树不包含值为 0 的节点
示例 3:
输入: root = [], key = 0
输出: []
提示:
节点数的范围 [0, 10^4].
-10^5 <= Node.val <= 10^5
节点值唯一
root 是合法的二叉搜索树
-10^5 <= key <= 10^5
进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
* https://leetcode.cn/problems/delete-node-in-a-bst/
 */
public class DeleteNodeBST {
    // 迭代 - 时间复杂度O(n) - 空间复杂度O(1)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 找到需要删除的节点和它的父节点
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null && cur.val != key) {
            if (key < cur.val) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        if (cur == null) {
            return root;
        }
        // 删除节点有两个子节点
        // 1。将删除节点先与其右子节点的最小值交换
        // 2。删除右子节点的最小值节点（按子节点只有一个或者没有的情况）
        if (cur.left != null && cur.right != null) {
            TreeNode minNode = cur.right;
            TreeNode minNodeParent = cur;
            while (minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }
            // 更新cur和parent，可以继续使用下一种情况（子节点只有一个或者没有）的代码
            cur = minNode;
            parent = minNodeParent;
        }
        // 删除节点只有一个或者没有子节点的情况
        // 保存删除节点的子节点, 替代当前删除节点
        TreeNode child = null;
        if (cur.left != null) {
            child = cur.left;
        } else if (cur.right != null) {
            child = cur.right;
        }
        // 替代当前删除节点
        if (parent == null) { // 无parent节点，删除节点为根节点
            return child;
        }
        if (parent.left == cur) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        return root;
    }

    // 递归 - 时间复杂度O(n) - 空间复杂度O(n)
    public TreeNode deleteNodeV2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // 找到root的右子树里的最小节点
            TreeNode minNode = root.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            // 将root的左子树 移动到 最小节点左子树（本来最小节点左子树为空）
            // root的右子树即为调整后的bst（删除了root节点，原root的左子树不变，依然符合bst规则）
            minNode.left = root.left;
            return root.right;
        } else if (root.left != null && key < root.val) {
            root.left = deleteNodeV2(root.left, key);
        } else if (root.right != null && key > root.val) {
            root.right = deleteNodeV2(root.right, key);
        }
        return root;
    }

    public static void main(String[] args) {
        DeleteNodeBST m = new DeleteNodeBST();
        SerializeDeserializeBinaryTree s = new SerializeDeserializeBinaryTree();
        TreeNode root = s.deserialize("5,3,6,2,4,null,7");
        TreeNode result = m.deleteNode(root, 3);
        System.out.println(s.serialize(result));
    }
}
