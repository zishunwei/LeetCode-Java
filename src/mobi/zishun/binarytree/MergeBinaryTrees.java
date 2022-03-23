package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 617. 合并二叉树
给你两棵二叉树： root1 和 root2 。
想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
你需要将这两棵树合并成一棵新二叉树。
* 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；
* 否则，不为 null 的节点将直接作为新二叉树的节点。
* 返回合并后的二叉树。
注意: 合并过程必须从两个树的根节点开始。
输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
输出：[3,4,5,5,4,null,7]
 */
public class MergeBinaryTrees {
    // dfs
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode result = new TreeNode(root1.val + root2.val);
        result.left = mergeTrees(root1.left, root2.left);
        result.right = mergeTrees(root1.right, root2.right);
        return result;
    }

    // stack
    public TreeNode mergeTreesV2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.addFirst(new TreeNode[]{root1, root2});
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.removeFirst();
            TreeNode node1 = cur[0];
            TreeNode node2 = cur[1];
            node1.val += node2.val;
            if (node1.left != null && node2.left != null) {
                stack.addFirst(new TreeNode[]{node1.left, node2.left});
            } else if (node2.left != null) {
                node1.left = node2.left;
            }
            if (node1.right != null && node2.right != null) {
                stack.addFirst(new TreeNode[]{node1.right, node2.right});
            } else if (node2.right != null) {
                node1.right = node2.right;
            }
        }
        return root1;
    }

    // dfs初版 - 把root2拼接赋值到root1 - 代码不简洁
    public TreeNode mergeTreesV3(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        TreeNode preNode = new TreeNode();
        // 存储父节点
        preNode.left = root1;
        // isLeft记录当前节点是父节点的左节点还是右节点
        dfs(root1, root2, preNode, true);
        return root1;
    }

    private void dfs(TreeNode root1, TreeNode root2, TreeNode parent, boolean isLeft) {
        if (root1 == null && root2 == null) {
            return;
        }
        if (root1 == null) {
            if (isLeft) {
                parent.left = root2;
            } else {
                parent.right = root2;
            }
        } else if (root2 != null) {
            root1.val += root2.val;
            dfs(root1.left, root2.left, root1, true);
            dfs(root1.right, root2.right, root1, false);
        }
    }


}
