package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，
* 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
叶子节点 是指没有子节点的节点。

示例 1：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
示例 2：
输入：root = [1,2,3], targetSum = 5
输出：[]
提示：
树中节点总数在范围 [0, 5000] 内
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*
 */
public class PathSumII {
    private final List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return result;
        }
        dfs(root, targetSum, new ArrayList<>());
        return result;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> temp) {
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                temp.add(root.val);
                result.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        }
        if (root.left != null) {
            temp.add(root.val);
            dfs(root.left, targetSum - root.val, temp);
            temp.remove(temp.size() - 1);
        }
        if (root.right != null) {
            temp.add(root.val);
            dfs(root.right, targetSum - root.val, temp);
            temp.remove(temp.size() - 1);
        }
    }

}