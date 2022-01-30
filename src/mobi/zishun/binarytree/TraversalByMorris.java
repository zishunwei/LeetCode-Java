package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class TraversalByMorris {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> result = new LinkedList<>();
        // 前驱节点（中序遍历）
        TreeNode preNode;
        while (root != null) {
            preNode = root.left;
            // 当前根节点有左子树
            if (preNode != null) {
                while (preNode.right != null && preNode.right != root) {
                    preNode = preNode.right;
                }
                // 前驱节点右节点为空-前序遍历直接打印Root
                if (preNode.right == null) {
                    preNode.right = root;
                    result.add(root.val);
                    root = root.left;
                }
                // 前驱节点右节点为当前根节点-说明左子树已遍历完-断开链接-遍历右子树
                else {
                    preNode.right = null;
                    root = root.right;
                }
            }
            // 当前根节点有左子树
            else {
                result.add(root.val);
                root = root.right;

            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> result = new LinkedList<>();
        // 前驱节点（中序遍历）
        TreeNode preNode;
        while (root != null) {
            preNode = root.left;
            // 当前根节点有左子树
            if (preNode != null) {
                while (preNode.right != null && preNode.right != root) {
                    preNode = preNode.right;
                }
                if (preNode.right == null) {
                    // preNode不指向root
                    preNode.right = root;
                    root = root.left;
                } else {
                    // preNode指向root
                    // 说明左子树已经访问完了-打印中节点-断开链接-遍历右子树
                    preNode.right = null;
                    result.add(root.val);
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

//    public List<Integer> postorderTraversal(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        List<Integer> result = new LinkedList<>();
//        return result;
//    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), null);
        TreeNode root = new TreeNode(1, left, right);

        TraversalByMorris traversalByMorris = new TraversalByMorris();
        System.out.println(traversalByMorris.preorderTraversal(root));
        System.out.println(traversalByMorris.inorderTraversal(root));
    }
}