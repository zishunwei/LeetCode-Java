package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
104. 二叉树的最大深度
给定一个二叉树，找出其最大深度。
二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
说明: 叶子节点是指没有子节点的节点。
 */
public class MaxDepthOfTree {
    // dfs
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        return leftMaxDepth >= rightMaxDepth ? 1 + leftMaxDepth : 1 + rightMaxDepth;
    }

    // bfs
    public static int maxDepthByBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);
        int res = 0;

        while (!queue.isEmpty()) {
            int currentLayerSize = queue.size();
            for (int i = 0; i < currentLayerSize; i++) {
                TreeNode cur = queue.removeLast();
                if (cur.left != null) {
                    queue.addFirst(cur.left);
                }
                if (cur.right != null) {
                    queue.addFirst(cur.right);
                }
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);

        System.out.println(maxDepthByBfs(root));
    }


}
