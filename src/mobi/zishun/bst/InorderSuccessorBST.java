package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 285. 二叉搜索树中的中序后继
给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。
* 如果节点没有中序后继，请返回 null 。
节点 p 的后继是值比 p.val 大的节点中键值最小的节点。

示例 1：
输入：root = [2,1,3], p = 1
输出：2
解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
示例 2：
输入：root = [5,3,6,2,4,null,null,1], p = 6
输出：null
解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
提示：
树中节点的数目在范围 [1, 10^4] 内。
-10^5 <= Node.val <= 10^5
树中各节点的值均保证唯一。
* https://leetcode.cn/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorBST {
    // 递归 - 中序遍历
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        target = p.val;
        dfs(root);
        return result;
    }

    // flag代表已找到p节点
    boolean flag = false;
    TreeNode result = null;
    int target;

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (result != null) {
            return;
        }
        dfs(root.left);
        if (flag) {
            result = root;
            flag = false;
        }
        if (root.val == target) {
            flag = true;
        }
        dfs(root.right);
    }

    // 栈 - 手动递归/中序遍历
    public TreeNode inorderSuccessorV2(TreeNode root, TreeNode p) {
        int target = p.val;
        boolean flag = false;
        TreeNode result = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        loop1:
        while (!stack.isEmpty()) {
            // 第一步：找到当前节点的最左的子节点
            TreeNode cur = stack.getFirst();
            while (cur.left != null) {
                stack.addFirst(cur.left);
                cur = cur.left;
            }
            // 第二步：输出并且将右子节点入栈
            while (!stack.isEmpty()) {
                cur = stack.removeFirst();
                if (flag) {
                    result = cur;
                    break loop1;
                }
                if (cur.val == target) {
                    flag = true;
                }
                // 存在则将右节点入栈，跳至第1步 （break退出第二步子循环）
                if (cur.right != null) {
                    stack.addFirst(cur.right);
                    break; // 一定要break，可能右子节点自己还有左子树，直接返回到上层循环才能使其左子树节点入栈
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        InorderSuccessorBST m = new InorderSuccessorBST();
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(m.inorderSuccessorV2(root, new TreeNode(3)));
    }
}