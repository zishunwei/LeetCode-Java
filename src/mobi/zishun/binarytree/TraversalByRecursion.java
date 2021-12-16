package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TraversalByRecursion {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        preorderTraversalRecursion(root, resList);
        return resList;
    }

    private static void preorderTraversalRecursion(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        resList.add(root.val);
        preorderTraversalRecursion(root.left, resList);
        preorderTraversalRecursion(root.right, resList);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        inorderTraversalRecursion(root, resList);
        return resList;
    }

    private static void inorderTraversalRecursion(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursion(root.left, resList);
        resList.add(root.val);
        inorderTraversalRecursion(root.right, resList);
    }


    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        postorderTraversalRecursion(root, resList);
        return resList;
    }

    private static void postorderTraversalRecursion(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        postorderTraversalRecursion(root.left, resList);
        postorderTraversalRecursion(root.right, resList);
        resList.add(root.val);
    }


    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);

        System.out.println(preorderTraversal(root));
        System.out.println(inorderTraversal(root));
        System.out.println(postorderTraversal(root));
    }

}
