package mobi.zishun.graph;

import mobi.zishun.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 129. 求根节点到叶节点数字之和
给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：
例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。
叶节点 是指没有子节点的节点。
链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return dfsRecur(root, 0);
    }

    private int dfsRecur(TreeNode curNode, int prevSum) {
        if (curNode == null) {
            return 0;
        }
        int curSum = prevSum * 10 + curNode.val;
        if (curNode.left == null && curNode.right == null) {
            return curSum;
        } else {
            return dfsRecur(curNode.left, curSum) + dfsRecur(curNode.right, curSum);
        }
    }

    public int sumNumbersBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);

        int res = 0;

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.remove();
            int curSum = sumQueue.remove();

            if (curNode.left == null && curNode.right == null) {
                res += curSum;
            } else {
                if (curNode.left != null) {
                    nodeQueue.add(curNode.left);
                    sumQueue.add(curSum * 10 + curNode.left.val);
                }
                if (curNode.right != null) {
                    nodeQueue.add(curNode.right);
                    sumQueue.add(curSum * 10 + curNode.right.val);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9, new TreeNode(5), new TreeNode(1));
        TreeNode right = new TreeNode(0);
        TreeNode root = new TreeNode(4, left, right);
        SumRootToLeafNumbers method = new SumRootToLeafNumbers();
        int res = method.sumNumbersBFS(root);
        System.out.println(res);
    }

}
