package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 111. 二叉树的最小深度
给定一个二叉树，找出其最小深度。
最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
说明：叶子节点是指没有子节点的节点。
 */
public class MinDepthOfTree {
    // 深度优先搜索-递归
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }

        int leftMinDepth = Integer.MAX_VALUE;
        int rightMinDepth = Integer.MAX_VALUE;
        // 注意：根节点
        if (root.right != null) {
            rightMinDepth = minDepth(root.right);
        }
        if (root.left != null) {
            leftMinDepth = minDepth(root.left);
        }
        return 1 + Math.min(leftMinDepth, rightMinDepth);
    }

    // 广度优先搜索-层序遍历
    public int minDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 1;

        while (!queue.isEmpty()) {
            int layerSize = queue.size();
            for (int i = 0; i < layerSize; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return res;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res++;
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode right = new TreeNode(3, new TreeNode(4), null);
        TreeNode left = new TreeNode(2, null, new TreeNode(5));
        TreeNode root = new TreeNode(1, left, right);

        MinDepthOfTree minDepthOfTree = new MinDepthOfTree();
        System.out.println(minDepthOfTree.minDepthV2(root));
    }

}
