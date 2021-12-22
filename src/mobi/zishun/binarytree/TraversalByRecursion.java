package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TraversalByRecursion {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        preorderTraversalRecursion(root, resList);
        return resList;
    }

    private void preorderTraversalRecursion(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        resList.add(root.val);
        preorderTraversalRecursion(root.left, resList);
        preorderTraversalRecursion(root.right, resList);
    }


    private final List<Integer> inorderTraversalResult = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return inorderTraversalResult;
        }
        inorderTraversalRecursion(root);
        return inorderTraversalResult;
    }

    public void inorderTraversalRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        inorderTraversalResult.add(root.val);
        inorderTraversal(root.right);
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        postorderTraversalRecursion(root, resList);
        return resList;
    }

    private void postorderTraversalRecursion(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        postorderTraversalRecursion(root.left, resList);
        postorderTraversalRecursion(root.right, resList);
        resList.add(root.val);
    }


    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4, new TreeNode(99), new TreeNode(99)));
        TreeNode right = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);

        TraversalByRecursion traversalByRecursion = new TraversalByRecursion();
        System.out.println(traversalByRecursion.preorderTraversal(root));
        System.out.println(traversalByRecursion.inorderTraversal(root));
        System.out.println(traversalByRecursion.postorderTraversal(root));
    }

}
