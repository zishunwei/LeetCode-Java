package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.HashSet;
import java.util.Set;

/*
 * 653. 两数之和 IV - 输入 BST
给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
输入: root = [5,3,6,2,4,null,7], k = 9
输出: true
* 提示:
- 二叉树的节点个数的范围是[1, 104].
- 104<= Node.val <= 104
- root为二叉搜索树
- 105<= k <= 105
链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 */
public class TwoSumBST {
    Set<Integer> hashSet = new HashSet<Integer>();

    // DFS - 此解并不考虑BST
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (hashSet.contains(k - root.val)) {
            return true;
        }
        hashSet.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
