package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * 437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

示例 1：
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
示例 2：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
提示:
二叉树的节点个数的范围是 [0,1000]
-10^9 <= Node.val <= 10^9
-1000 <= targetSum <= 1000
* https://leetcode-cn.com/problems/path-sum-iii/
 */
public class PathSumIII {
    // 前缀和 + 回溯 - O(n)
    // key是前缀和， value是该前缀和的节点数量，记录数量是因为有出现复数路径的可能。
    private final Map<Integer, Integer> prefix = new HashMap<>();

    private int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 为0的前缀和初始即有一个
        prefix.put(0, 1);
        dfs(root, root.val, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int curSum, int targetSum) {
        // 此次向下遍历里有符合条件的之前的前缀和
        if (prefix.containsKey(curSum - targetSum)) {
            // 有几个符合条件的节点数量 - 即代表有几种结果
            res += prefix.get(curSum - targetSum);
        }
        // 记录当前前缀和，数量+1
        prefix.put(curSum, prefix.getOrDefault(curSum, 0) + 1);
        if (root.left != null) {
            dfs(root.left, curSum + root.left.val, targetSum);
        }
        if (root.right != null) {
            dfs(root.right, curSum + root.right.val, targetSum);
        }
        // 回溯 - 避免左右子树互相影响（不如右子树上的节点找到map里左子树的节点）
        prefix.put(curSum, prefix.get(curSum) - 1);
    }


    // dfs - O(n^2)
    public int pathSumV2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 从当前节点出发满足条件的个数
        int res = rootPathSum(root, targetSum);
        // 左右子树的答案
        res += pathSumV2(root.left, targetSum);
        res += pathSumV2(root.right, targetSum);
        return res;
    }

    // 从root出发的路径和
    private int rootPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val == targetSum) {
            res++;
        }
        res += rootPathSum(root.left, targetSum - root.val);
        res += rootPathSum(root.right, targetSum - root.val);
        return res;
    }

    public static void main(String[] args) {
        PathSumIII m = new PathSumIII();
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(1), null), new TreeNode(3));
//        System.out.println(m.rootPathSum(root, 3));
        System.out.println(m.pathSum(root, 3));
    }
}
