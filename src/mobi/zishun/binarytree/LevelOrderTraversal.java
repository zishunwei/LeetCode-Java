package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LevelOrderTraversal {
    // 广度优先
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> result = new ArrayList<>();

        // 使用队列实现。出队的同时，把他的子节点依次入队。
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            List<Integer> oneLayer = new ArrayList<>();

            int currentLayerSize = queue.size();
            // 广度优先、循环分层
            for (int i = 0; i < currentLayerSize; i++) {
                TreeNode cur = queue.removeFirst();
                oneLayer.add(cur.val);
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            result.add(oneLayer);
        }
        return result;
    }

    // 深度优先
    // 遍历二叉树、按层数写入对应数组位置
    public static List<List<Integer>> levelOrderByDfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> res = new ArrayList<>();
        // index为层数记录
        recursion(1, root, res);
        return res;
    }

    private static void recursion(int index, TreeNode root, List<List<Integer>> res) {
        // 层数对应数组大小
        if (res.size() < index) {
            res.add(new ArrayList<>());
        }
        // 把遍历到的节点加到对应层数的数组里
        // 层数-1等于数组对应下标
        res.get(index - 1).add(root.val);

        if (root.left != null) {
            recursion(index + 1, root.left, res);
        }
        if (root.right != null) {
            recursion(index + 1, root.right, res);
        }
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, null, new TreeNode(7, null, new TreeNode(8)));
        TreeNode root = new TreeNode(1, left, right);

        System.out.println(levelOrder(root));
    }
}