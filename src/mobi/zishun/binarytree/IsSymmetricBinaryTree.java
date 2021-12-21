package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 101. 对称二叉树
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
*
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3
 */
public class IsSymmetricBinaryTree {
    // 递归
    // 该问题可以转化为：两个树在什么情况下互为镜像？
    // 如果同时满足下面的条件，两个树互为镜像：
    // 1. 它们的两个根结点具有相同的值
    // 2. 每个树的右子树都与另一个树的左子树镜像对称
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricRecursion(root.left, root.right);
    }

    private boolean isSymmetricRecursion(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        return leftNode.val == rightNode.val && isSymmetricRecursion(leftNode.right, rightNode.left)
                && isSymmetricRecursion(leftNode.left, rightNode.right);
    }

    // 迭代实现
    public boolean isSymmetricV2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricIteration(root.left, root.right);
    }

    // 0。 首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。
    // 1。 初始化时我们把根节点入队两次。
    // 2。 每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），
    // 3。 然后将两个结点的左右子结点按相反的顺序插入队列中。
    // 4。 当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
    private boolean isSymmetricIteration(TreeNode leftNode, TreeNode rightNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(rightNode);
        queue.add(leftNode);

        while (!queue.isEmpty()) {
            TreeNode first = queue.remove();
            TreeNode second = queue.remove();
            // 子节点有空值的情况
            if (first == null && second == null) {
                // 直接开始下一次迭代
                continue;
            }

            if ((first == null || second == null) || first.val != second.val) {
                return false;
            }

            queue.add(first.left);
            queue.add(second.right);

            queue.add(first.right);
            queue.add(second.left);

        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(5), new TreeNode(4));
        TreeNode right = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        TreeNode root = new TreeNode(1, left, right);

        IsSymmetricBinaryTree isSymmetricBinaryTree = new IsSymmetricBinaryTree();
        System.out.println(isSymmetricBinaryTree.isSymmetricV2(root));
    }

    // 难以实现-层序遍历使用栈的方向错误
    // [2,3,3,4,5,5,4,null,null,8,9,null,null,9,8] 判例无法通过
    public boolean isSymmetricFail(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        stack.push(root);
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.removeLast();
                if (!stack.isEmpty() && cur.val == stack.getFirst().val) {
                    stack.removeFirst();
                } else {
                    stack.addFirst(cur);
                }
                if (cur.left != null) {
                    queue.addFirst(cur.left);
                } else if (cur.right != null) {
                    queue.addFirst(new TreeNode(Integer.MIN_VALUE));
                }
                if (cur.right != null) {
                    queue.addFirst(cur.right);
                } else if (cur.left != null) {
                    queue.addFirst(new TreeNode(Integer.MIN_VALUE));
                }
            }
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}